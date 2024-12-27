package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/send", JSImport.Default)
private object SendIcon extends js.Array[js.Any]

def Send(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SendIcon, color, size)
