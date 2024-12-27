package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/speech", JSImport.Default)
private object SpeechIcon extends js.Array[js.Any]

def Speech(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SpeechIcon, color, size)
