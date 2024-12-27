package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pilcrow", JSImport.Default)
private object PilcrowIcon extends js.Array[js.Any]

def Pilcrow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PilcrowIcon, color, size)
