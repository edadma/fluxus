import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / licenses += "ISC"  -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme      := Some("semver-spec")
ThisBuild / evictionErrorLevel := Level.Warn
ThisBuild / scalaVersion       := "3.6.2"
ThisBuild / organization       := "io.github.edadma"
ThisBuild / version            := "0.0.2"

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
  ),
  scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
//  scalaJSLinkerConfig ~= { _.withModuleSplitStyle(ModuleSplitStyle.SmallestModules) },
  scalaJSLinkerConfig ~= { _.withSourceMap(false) },
  githubOwner      := "edadma",
  githubRepository := "fluxus",
)

lazy val library = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)
//  .enablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    name := "fluxus",
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
    Test / scalaJSUseMainModuleInitializer := false,
    Test / scalaJSUseTestModuleInitializer := true,
    Test / parallelExecution               := false,
    publishMavenStyle                      := true,
    Test / publishArtifact                 := false,
  )

lazy val examples = project
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(library)
  .settings(commonSettings)
  .settings(
    name                            := "examples",
    scalaJSUseMainModuleInitializer := true,
    publish / skip                  := true,
    publishLocal / skip             := true,
  )

lazy val fluxus = project
  .in(file("."))
  .aggregate(library, examples)
  .settings(
    publish      := {},
    publishLocal := {},
  )
