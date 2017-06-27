name := "quill-example"

version := "1.0"

scalaVersion := "2.12.2"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "io.getquill" %% "quill-async-postgres" % "1.0.1-SNAPSHOT"
)

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import",
  "-Xlog-implicits"
)
