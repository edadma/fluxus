package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/audio-waveform", JSImport.Default)
private object AudioWaveformIcon extends js.Array[js.Any]

def AudioWaveform(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AudioWaveformIcon, color, size)
