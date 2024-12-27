package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ethernet-port", JSImport.Default)
private object EthernetPortIcon extends js.Array[js.Any]

def EthernetPort(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EthernetPortIcon, color, size)
