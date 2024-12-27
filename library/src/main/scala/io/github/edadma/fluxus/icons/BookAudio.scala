package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-audio", JSImport.Default)
private object BookAudioIcon extends js.Array[js.Any]

def BookAudio(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookAudioIcon, color, size)
