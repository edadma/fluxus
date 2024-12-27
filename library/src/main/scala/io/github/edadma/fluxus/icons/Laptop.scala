package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/laptop", JSImport.Default)
private object LaptopIcon extends js.Array[js.Any]

def Laptop(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LaptopIcon, color, size)
