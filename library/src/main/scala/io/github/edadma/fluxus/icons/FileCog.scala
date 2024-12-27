package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-cog", JSImport.Default)
private object FileCogIcon extends js.Array[js.Any]

def FileCog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileCogIcon, color, size)
