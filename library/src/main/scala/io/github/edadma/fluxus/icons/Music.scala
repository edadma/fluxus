package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/music", JSImport.Default)
private object MusicIcon extends js.Array[js.Any]

def Music(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MusicIcon, color, size)
