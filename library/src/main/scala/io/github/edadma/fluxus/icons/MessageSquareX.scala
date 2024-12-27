package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-x", JSImport.Default)
private object MessageSquareXIcon extends js.Array[js.Any]

def MessageSquareX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareXIcon, color, size)
