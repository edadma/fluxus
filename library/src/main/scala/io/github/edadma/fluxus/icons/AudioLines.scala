package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/audio-lines", JSImport.Default)
private object AudioLinesIcon extends js.Array[js.Any]

def AudioLines(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AudioLinesIcon, color, size)
