package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lectern", JSImport.Default)
private object LecternIcon extends js.Array[js.Any]

def Lectern(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LecternIcon, color, size)
