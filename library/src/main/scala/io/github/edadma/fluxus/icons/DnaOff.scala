package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dna-off", JSImport.Default)
private object DnaOffIcon extends js.Array[js.Any]

def DnaOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DnaOffIcon, color, size)
