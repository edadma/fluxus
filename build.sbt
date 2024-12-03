ThisBuild / licenses += "ISC"  -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme      := Some("semver-spec")
ThisBuild / evictionErrorLevel := Level.Warn

publish / skip := true

lazy val fluxus = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
//  .enablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    name             := "fluxus",
    version          := "0.0.1",
    scalaVersion     := "3.5.2",
    organization     := "io.github.edadma",
    githubOwner      := "edadma",
    githubRepository := name.value,
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest"   % "3.2.19" % "test",
      "com.lihaoyi"   %%% "pprint"      % "0.9.0"  % "test",
      "org.scala-js"  %%% "scalajs-dom" % "2.4.0",
    ),
    Test / npmDependencies ++= Seq(
      "jsdom" -> "25.0.1",
    ),
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
//    Test / scalaJSUseMainModuleInitializer := true,
//    Test / scalaJSUseTestModuleInitializer := false,
    Test / scalaJSUseMainModuleInitializer := false,
    Test / scalaJSUseTestModuleInitializer := true,
    scalaJSUseMainModuleInitializer        := true,
//    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    publishMavenStyle      := true,
    Test / publishArtifact := false,
  )
