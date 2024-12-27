package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/laptop-minimal", JSImport.Default)
private object LaptopMinimalIcon extends js.Array[js.Any]

def LaptopMinimal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LaptopMinimalIcon, color, size)
