package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/menu", JSImport.Default)
private object MenuIcon extends js.Array[js.Any]

def Menu(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MenuIcon, color, size)
