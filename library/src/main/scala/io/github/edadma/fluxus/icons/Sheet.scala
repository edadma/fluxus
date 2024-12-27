package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sheet", JSImport.Default)
private object SheetIcon extends js.Array[js.Any]

def Sheet(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SheetIcon, color, size)
