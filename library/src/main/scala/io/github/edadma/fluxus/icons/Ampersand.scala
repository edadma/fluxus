package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ampersand", JSImport.Default)
private object AmpersandIcon extends js.Array[js.Any]

def Ampersand(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AmpersandIcon, color, size)
