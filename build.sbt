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
    scalaVersion     := "3.6.2",
    organization     := "io.github.edadma",
    githubOwner      := "edadma",
    githubRepository := name.value,
    libraryDependencies ++= Seq(
      "org.scalatest"    %%% "scalatest"   % "3.2.19" % "test",
      "com.lihaoyi"      %%% "pprint"      % "0.9.0"  % "test",
      "org.scala-js"     %%% "scalajs-dom" % "2.8.0",
      "io.github.edadma" %%% "logger"      % "0.0.5",
      "dev.zio"          %%% "zio-json"    % "0.7.3",
      "com.raquo"        %%% "airstream"   % "16.0.0",
    ),
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
//    Test / scalaJSUseMainModuleInitializer := true,
//    Test / scalaJSUseTestModuleInitializer := false,
    Test / parallelExecution               := false,
    Test / scalaJSUseMainModuleInitializer := false,
    Test / scalaJSUseTestModuleInitializer := true,
//    Test / jsEnv                           := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
//      org.scalajs.jsenv.nodejs.NodeJSEnv.Config()
//        .withEnv(Map(
//          "NODE_PATH" -> "node_modules",
//        ))
//        .withArgs(List("--require", "jsdom-global/register")),
//    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    publishMavenStyle      := true,
    Test / publishArtifact := false,
  )
