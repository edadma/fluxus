package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-lock-2", JSImport.Default)
private object FileLock2Icon extends js.Array[js.Any]

def FileLock2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileLock2Icon, color, size)
