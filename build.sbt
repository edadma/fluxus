ThisBuild / licenses += "ISC"  -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme      := Some("semver-spec")
ThisBuild / evictionErrorLevel := Level.Warn

publish / skip := true

lazy val fluxus = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
//  .enablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    name             := "fluxus",
    version          := "0.0.1",
    scalaVersion     := "3.5.2",
    organization     := "io.github.edadma",
    githubOwner      := "edadma",
    githubRepository := name.value,
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0",
      "org.scalatest"     %%% "scalatest"       % "3.2.19" % "test",
      "com.lihaoyi"       %%% "pprint"          % "0.9.0"  % "test",
//      "org.scala-js"      %%% "scalajs-logging" % "1.1.1",
      "com.raquo"    %%% "domtypes"    % "0.15.0",
      "org.scala-js" %%% "scalajs-dom" % "2.4.0",
    ),
    //    Compile / npmDependencies ++= Seq(
//      "socket.io" -> "4.7.3",
//    ),
    jsEnv                                  := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    Test / scalaJSUseMainModuleInitializer := true,
    Test / scalaJSUseTestModuleInitializer := false,
//    Test / scalaJSUseMainModuleInitializer := false,
//    Test / scalaJSUseTestModuleInitializer := true,
    scalaJSUseMainModuleInitializer := true,
//    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    publishMavenStyle      := true,
    Test / publishArtifact := false,
    licenses += "ISC"      -> url("https://opensource.org/licenses/ISC"),
  )
