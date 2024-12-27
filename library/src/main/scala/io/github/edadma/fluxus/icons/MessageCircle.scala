package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle", JSImport.Default)
private object MessageCircleIcon extends js.Array[js.Any]

def MessageCircle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleIcon, color, size)
