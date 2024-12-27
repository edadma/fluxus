package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-input", JSImport.Default)
private object FolderInputIcon extends js.Array[js.Any]

def FolderInput(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderInputIcon, color, size)
