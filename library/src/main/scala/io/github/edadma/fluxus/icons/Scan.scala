package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan", JSImport.Default)
private object ScanIcon extends js.Array[js.Any]

def Scan(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanIcon, color, size)
