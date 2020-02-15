import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "slick-bug",
    libraryDependencies += scalaTest % Test
  )

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "org.postgresql" % "postgresql" % "42.2.5" //org.postgresql.ds.PGSimpleDataSource dependency
)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
