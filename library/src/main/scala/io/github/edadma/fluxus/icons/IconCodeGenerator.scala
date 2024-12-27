package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import js.Dynamic.global

@js.native
@JSImport("fs", JSImport.Namespace)
private object fs extends js.Object {
  def mkdirSync(path: String, options: js.Dynamic): Unit = js.native

  def readdirSync(path: String): js.Array[String] = js.native

  def writeFileSync(path: String, data: String): Unit = js.native
}

object IconCodeGenerator:
  val IconsDir  = "node_modules/lucide/dist/esm/icons"
  val OutputDir = "library/src/main/scala/io/github/edadma/fluxus/icons"

  def generateIconFiles(): Unit =
    // Create output directory
    fs.mkdirSync(OutputDir, js.Dynamic.literal(recursive = true))

    // Read icons directory
    val iconFiles = fs.readdirSync(IconsDir).asInstanceOf[js.Array[String]]
      .filter(_.endsWith(".js"))
      .map(_.stripSuffix(".js"))

    // Generate one file per icon
    iconFiles.foreach { icon =>
      val scalaName = kebabToScala(icon) // e.g., "book-open" -> "BookOpen"
      generateIconFile(icon, scalaName)
    }

  def kebabToScala(kebab: String): String =
    kebab.split("-").map(_.capitalize).mkString

  def generateIconFile(iconName: String, scalaName: String): Unit =
    val code = s"""package io.github.edadma.fluxus.icons
                  |
                  |import scala.scalajs.js
                  |import scala.scalajs.js.annotation.JSImport
                  |import io.github.edadma.fluxus.RawNode
                  |
                  |@js.native
                  |@JSImport("lucide/dist/esm/icons/$iconName", JSImport.Default)
                  |private object ${scalaName}Icon extends js.Array[js.Any]
                  |
                  |def $scalaName(size: Int = 24, color: String = "currentColor"): RawNode =
                  |  createIcon(${scalaName}Icon, color, size)
                  |""".stripMargin

    fs.writeFileSync(s"$OutputDir/$scalaName.scala", code)

@main def generate(): Unit = IconCodeGenerator.generateIconFiles()
