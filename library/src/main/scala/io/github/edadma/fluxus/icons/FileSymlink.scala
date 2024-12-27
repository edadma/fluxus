package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-symlink", JSImport.Default)
private object FileSymlinkIcon extends js.Array[js.Any]

def FileSymlink(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileSymlinkIcon, color, size)
