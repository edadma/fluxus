package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan-line", JSImport.Default)
private object ScanLineIcon extends js.Array[js.Any]

def ScanLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanLineIcon, color, size)
