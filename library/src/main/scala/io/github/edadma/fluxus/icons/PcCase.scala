package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pc-case", JSImport.Default)
private object PcCaseIcon extends js.Array[js.Any]

def PcCase(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PcCaseIcon, color, size)
