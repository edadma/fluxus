ThisBuild / licenses += "ISC"      -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme          := Some("semver-spec")
ThisBuild / evictionErrorLevel     := Level.Warn
ThisBuild / scalaVersion           := "3.7.2"
ThisBuild / organization           := "io.github.edadma"
ThisBuild / organizationName       := "edadma"
ThisBuild / organizationHomepage   := Some(url("https://github.com/edadma"))
ThisBuild / version                := "0.0.34"
ThisBuild / sonatypeCredentialHost := "central.sonatype.com"

ThisBuild / publishConfiguration := publishConfiguration.value.withOverwrite(true).withChecksums(Vector.empty)
ThisBuild / resolvers += Resolver.mavenLocal
ThisBuild / resolvers += Resolver.sonatypeCentralSnapshots
ThisBuild / resolvers += Resolver.sonatypeCentralRepo("releases")

ThisBuild / sonatypeProfileName := "io.github.edadma"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/edadma/fluxus"),
    "scm:git@github.com:edadma/fluxus.git",
  ),
)
ThisBuild / developers := List(
  Developer(
    id = "edadma",
    name = "Edward A. Maxedon, Sr.",
    email = "edadma@gmail.com",
    url = url("https://github.com/edadma"),
  ),
)

ThisBuild / homepage := Some(url("https://github.com/edadma/fluxus"))

ThisBuild / pomIncludeRepository := { _ => false }

ThisBuild / publishTo := {
  val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
  if (isSnapshot.value) Some("central-snapshots" at centralSnapshots)
  else localStaging.value
}

ThisBuild / publishMavenStyle := true

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
)

lazy val library = project
  .enablePlugins(ScalaJSPlugin, ParadoxSitePlugin, SitePreviewPlugin, ParadoxMaterialThemePlugin)
  .settings(commonSettings)
  .settings(
    name        := "fluxus",
    description := "A minimalist UI framework inspired by component-based design, built with Scala.js",
    libraryDependencies ++= Seq(
      "org.scalatest"    %%% "scalatest"                   % "3.2.19" % "test",
      "com.lihaoyi"      %%% "pprint"                      % "0.9.3"  % "test",
      "org.scala-js"     %%% "scalajs-dom"                 % "2.8.0",
      "io.github.edadma" %%% "logger"                      % "0.0.6",
      "dev.zio"          %%% "zio-json"                    % "0.7.44",
      "com.raquo"        %%% "airstream"                   % "17.2.1",
      "org.scala-js"     %%% "scala-js-macrotask-executor" % "1.1.1",
    ),
    jsEnv                                  := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    scalaJSUseMainModuleInitializer        := true,
    Test / scalaJSUseMainModuleInitializer := false,
    Test / scalaJSUseTestModuleInitializer := true,
    Test / parallelExecution               := false,
    Test / publishArtifact                 := false,
  )

lazy val examples = project
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(library % "compile->compile;test->test")
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
    name                := "fluxus",
    publish / skip      := true,
    publishLocal / skip := true,
  )
