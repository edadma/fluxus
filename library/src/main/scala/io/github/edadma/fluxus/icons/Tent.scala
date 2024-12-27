package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tent", JSImport.Default)
private object TentIcon extends js.Array[js.Any]

def Tent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TentIcon, color, size)
