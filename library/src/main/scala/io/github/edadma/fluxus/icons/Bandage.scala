package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bandage", JSImport.Default)
private object BandageIcon extends js.Array[js.Any]

def Bandage(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BandageIcon, color, size)
