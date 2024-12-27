package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/printer-check", JSImport.Default)
private object PrinterCheckIcon extends js.Array[js.Any]

def PrinterCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PrinterCheckIcon, color, size)
