package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-warning", JSImport.Default)
private object FileWarningIcon extends js.Array[js.Any]

def FileWarning(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileWarningIcon, color, size)
