package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan-text", JSImport.Default)
private object ScanTextIcon extends js.Array[js.Any]

def ScanText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanTextIcon, color, size)
