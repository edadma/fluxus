package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flower", JSImport.Default)
private object FlowerIcon extends js.Array[js.Any]

def Flower(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlowerIcon, color, size)
