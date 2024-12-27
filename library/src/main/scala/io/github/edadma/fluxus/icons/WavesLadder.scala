package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/waves-ladder", JSImport.Default)
private object WavesLadderIcon extends js.Array[js.Any]

def WavesLadder(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WavesLadderIcon, color, size)
