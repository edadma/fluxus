package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bed-double", JSImport.Default)
private object BedDoubleIcon extends js.Array[js.Any]

def BedDouble(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BedDoubleIcon, color, size)
