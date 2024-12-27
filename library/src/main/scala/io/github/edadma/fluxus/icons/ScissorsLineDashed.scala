package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scissors-line-dashed", JSImport.Default)
private object ScissorsLineDashedIcon extends js.Array[js.Any]

def ScissorsLineDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScissorsLineDashedIcon, color, size)
