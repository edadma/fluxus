package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/printer", JSImport.Default)
private object PrinterIcon extends js.Array[js.Any]

def Printer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PrinterIcon, color, size)
