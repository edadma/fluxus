package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-off", JSImport.Default)
private object MessageCircleOffIcon extends js.Array[js.Any]

def MessageCircleOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleOffIcon, color, size)
