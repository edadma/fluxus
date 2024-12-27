package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/speaker", JSImport.Default)
private object SpeakerIcon extends js.Array[js.Any]

def Speaker(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SpeakerIcon, color, size)
