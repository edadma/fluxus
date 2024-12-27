package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/magnet", JSImport.Default)
private object MagnetIcon extends js.Array[js.Any]

def Magnet(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MagnetIcon, color, size)
