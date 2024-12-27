package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/amphora", JSImport.Default)
private object AmphoraIcon extends js.Array[js.Any]

def Amphora(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AmphoraIcon, color, size)
