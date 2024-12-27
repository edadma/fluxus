package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-code", JSImport.Default)
private object MessageCircleCodeIcon extends js.Array[js.Any]

def MessageCircleCode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleCodeIcon, color, size)
