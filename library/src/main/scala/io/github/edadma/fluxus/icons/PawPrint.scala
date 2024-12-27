package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/paw-print", JSImport.Default)
private object PawPrintIcon extends js.Array[js.Any]

def PawPrint(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PawPrintIcon, color, size)
