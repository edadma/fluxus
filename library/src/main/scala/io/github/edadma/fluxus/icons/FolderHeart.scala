package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-heart", JSImport.Default)
private object FolderHeartIcon extends js.Array[js.Any]

def FolderHeart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderHeartIcon, color, size)
