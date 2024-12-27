package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/omega", JSImport.Default)
private object OmegaIcon extends js.Array[js.Any]

def Omega(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OmegaIcon, color, size)
