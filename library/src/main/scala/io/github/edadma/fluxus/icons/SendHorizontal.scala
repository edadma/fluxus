package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/send-horizontal", JSImport.Default)
private object SendHorizontalIcon extends js.Array[js.Any]

def SendHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SendHorizontalIcon, color, size)
