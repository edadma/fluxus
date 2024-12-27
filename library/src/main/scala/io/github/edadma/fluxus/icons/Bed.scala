package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bed", JSImport.Default)
private object BedIcon extends js.Array[js.Any]

def Bed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BedIcon, color, size)
