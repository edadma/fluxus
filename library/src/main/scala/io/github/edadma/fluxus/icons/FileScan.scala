package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-scan", JSImport.Default)
private object FileScanIcon extends js.Array[js.Any]

def FileScan(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileScanIcon, color, size)
