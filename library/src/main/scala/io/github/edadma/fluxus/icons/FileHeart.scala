package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-heart", JSImport.Default)
private object FileHeartIcon extends js.Array[js.Any]

def FileHeart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileHeartIcon, color, size)
