package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan-search", JSImport.Default)
private object ScanSearchIcon extends js.Array[js.Any]

def ScanSearch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanSearchIcon, color, size)
