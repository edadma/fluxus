package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bed-single", JSImport.Default)
private object BedSingleIcon extends js.Array[js.Any]

def BedSingle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BedSingleIcon, color, size)
