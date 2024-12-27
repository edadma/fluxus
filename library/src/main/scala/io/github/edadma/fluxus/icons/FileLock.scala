package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-lock", JSImport.Default)
private object FileLockIcon extends js.Array[js.Any]

def FileLock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileLockIcon, color, size)
