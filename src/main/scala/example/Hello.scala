package example

import slick.jdbc.PostgresProfile.api._
import slick.jdbc.DatabaseUrlDataSource

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.duration._
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

case class Action(id: Int, title: String)

object Action {
  class Actions(tag: Tag) extends
    Table[Action](tag, "actions") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")

    def * = (id, title) <> ((Action.apply _).tupled, Action.unapply)
  }
  val records = TableQuery[Actions]
}

object PostgresDatabase extends App {
  val db = Database.forDataSource(new DatabaseUrlDataSource(), None)

  val action = DBIO.seq(
    // this does not create sequence in Postgres, test with `\ds` in `psql` shell
    (Action.records.schema).createIfNotExists,

    // this will fail, try changing above to `create` and it will work
    Action.records += Action(0, "Test")
  )

  println(Await.result(db.run(action), 5.seconds))
}
