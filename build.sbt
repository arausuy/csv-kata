name := "markit"

version := "1.0"

organization := "io.github.arausuy"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.13.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)
