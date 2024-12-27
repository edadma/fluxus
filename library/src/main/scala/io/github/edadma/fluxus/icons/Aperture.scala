package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/aperture", JSImport.Default)
private object ApertureIcon extends js.Array[js.Any]

def Aperture(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ApertureIcon, color, size)
