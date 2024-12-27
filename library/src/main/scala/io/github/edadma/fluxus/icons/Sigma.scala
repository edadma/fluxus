package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sigma", JSImport.Default)
private object SigmaIcon extends js.Array[js.Any]

def Sigma(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SigmaIcon, color, size)
