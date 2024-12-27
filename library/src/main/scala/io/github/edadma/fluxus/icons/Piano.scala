package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/piano", JSImport.Default)
private object PianoIcon extends js.Array[js.Any]

def Piano(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PianoIcon, color, size)
