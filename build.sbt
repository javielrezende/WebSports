name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.8"

libraryDependencies += javaJdbc
libraryDependencies += cache
libraryDependencies += javaWs
libraryDependencies += evolutions
libraryDependencies ++= Seq(
  "be.objectify" %% "deadbolt-java" % "2.5.0"
)

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.0"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.0"

libraryDependencies ++= Seq(
"it.innove" % "play2-pdf" % "1.6.0"
)