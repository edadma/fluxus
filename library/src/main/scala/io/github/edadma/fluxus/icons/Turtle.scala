package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/turtle", JSImport.Default)
private object TurtleIcon extends js.Array[js.Any]

def Turtle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TurtleIcon, color, size)
