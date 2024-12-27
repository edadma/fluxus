package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/copy-slash", JSImport.Default)
private object CopySlashIcon extends js.Array[js.Any]

def CopySlash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CopySlashIcon, color, size)
