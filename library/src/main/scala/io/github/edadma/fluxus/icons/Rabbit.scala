package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rabbit", JSImport.Default)
private object RabbitIcon extends js.Array[js.Any]

def Rabbit(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RabbitIcon, color, size)
