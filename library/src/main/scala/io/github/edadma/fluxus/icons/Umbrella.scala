package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/umbrella", JSImport.Default)
private object UmbrellaIcon extends js.Array[js.Any]

def Umbrella(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UmbrellaIcon, color, size)
