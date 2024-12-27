package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/milk", JSImport.Default)
private object MilkIcon extends js.Array[js.Any]

def Milk(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MilkIcon, color, size)
