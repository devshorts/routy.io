import com.tuplejump.sbt.yeoman.Yeoman
import com.typesafe.sbt.SbtScalariform._
import play.PlayScala

import scalariform.formatter.preferences._

//********************************************************
// Play settings
//********************************************************

name := """routyio"""

version := "2.0-RC2"

scalaVersion := "2.11.6"

resolvers := ("Atlassian Releases" at "https://maven.atlassian.com/public/") +: resolvers.value

resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "com.mohiva" %% "play-silhouette" % "2.0-RC2",
  "net.codingwell" %% "scala-guice" % "4.0.0-beta5",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "xalan" % "serializer" % "2.7.2",
  "org.mongodb" %% "casbah" % "3.0.0",
  cache
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
//  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen" // Warn when numerics are widened.
)

//********************************************************
// Yeoman settings
//********************************************************
Yeoman.yeomanSettings

//********************************************************
// Scalariform settings
//********************************************************

defaultScalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(FormatXml, false)
  .setPreference(DoubleIndentClassDeclaration, false)
  .setPreference(PreserveDanglingCloseParenthesis, true)
