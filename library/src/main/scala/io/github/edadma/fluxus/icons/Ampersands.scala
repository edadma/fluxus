package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ampersands", JSImport.Default)
private object AmpersandsIcon extends js.Array[js.Any]

def Ampersands(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AmpersandsIcon, color, size)
