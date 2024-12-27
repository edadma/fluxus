package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-user", JSImport.Default)
private object FileUserIcon extends js.Array[js.Any]

def FileUser(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileUserIcon, color, size)
