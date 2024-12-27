package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dna", JSImport.Default)
private object DnaIcon extends js.Array[js.Any]

def Dna(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DnaIcon, color, size)
