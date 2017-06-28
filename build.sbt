name := "quill-example"

val scalaVer = "2.12.2"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := scalaVer,
  scalacOptions ++= Seq(
    "-target:jvm-1.8"
    , "-feature"
    , "-deprecation"
    , "-Xfatal-warnings"
    , "-Xmax-classfile-name", "100"
    , "-unchecked"
    , "-language:implicitConversions"
    , "-language:reflectiveCalls"
    , "-language:postfixOps"
    , "-language:higherKinds"
    , "-encoding", "UTF-8"
    , "-Yno-adapted-args"
    , "-Xlint"
    , "-Ywarn-numeric-widen"
    , "-Ywarn-value-discard"
    , "-Xfuture"
//    , "-Xlog-implicits"
  ),
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint"),
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots")
  )
)

val quillVer = "1.2.2-SNAPSHOT"

lazy val macrosModule = project.in(file("macro"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect",
      "org.scala-lang" % "scala-compiler"
    ).map(_  % scalaVer),
    libraryDependencies ++= Seq(
      "io.getquill" %% "quill-async-postgres",
      "io.getquill" %% "quill-jdbc"
    ).map(_ % quillVer),
    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "9.4.1208"
    )
  )

lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "9.4.1208"
    )
  )
  .dependsOn(macrosModule)
