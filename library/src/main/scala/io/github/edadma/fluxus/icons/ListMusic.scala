package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-music", JSImport.Default)
private object ListMusicIcon extends js.Array[js.Any]

def ListMusic(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListMusicIcon, color, size)
