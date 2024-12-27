package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/keyboard-music", JSImport.Default)
private object KeyboardMusicIcon extends js.Array[js.Any]

def KeyboardMusic(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(KeyboardMusicIcon, color, size)
