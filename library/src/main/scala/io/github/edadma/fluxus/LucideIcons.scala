package io.github.edadma.fluxus

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("lucide", JSImport.Namespace)
object LucideIcons extends js.Object {
  // Each icon is an array: [tag, attributes, children]
  // We'll type it more precisely to help with conversion
  type IconElement = js.Array[js.Any]

  // Import specific icons we want
  val Camera: IconElement    = js.native
  val CircleDot: IconElement = js.native
  // etc...
}
