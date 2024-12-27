package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folders", JSImport.Default)
private object FoldersIcon extends js.Array[js.Any]

def Folders(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FoldersIcon, color, size)
