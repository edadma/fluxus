package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-badge-2", JSImport.Default)
private object FileBadge2Icon extends js.Array[js.Any]

def FileBadge2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileBadge2Icon, color, size)
