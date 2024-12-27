package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-share", JSImport.Default)
private object MessageSquareShareIcon extends js.Array[js.Any]

def MessageSquareShare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareShareIcon, color, size)
