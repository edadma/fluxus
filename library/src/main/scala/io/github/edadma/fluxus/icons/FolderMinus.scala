package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-minus", JSImport.Default)
private object FolderMinusIcon extends js.Array[js.Any]

def FolderMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderMinusIcon, color, size)
