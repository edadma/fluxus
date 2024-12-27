package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-music", JSImport.Default)
private object FileMusicIcon extends js.Array[js.Any]

def FileMusic(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileMusicIcon, color, size)
