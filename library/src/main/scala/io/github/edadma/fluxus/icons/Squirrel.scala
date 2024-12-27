package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/squirrel", JSImport.Default)
private object SquirrelIcon extends js.Array[js.Any]

def Squirrel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquirrelIcon, color, size)
