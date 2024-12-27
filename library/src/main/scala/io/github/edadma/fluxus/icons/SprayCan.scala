package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/spray-can", JSImport.Default)
private object SprayCanIcon extends js.Array[js.Any]

def SprayCan(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SprayCanIcon, color, size)
