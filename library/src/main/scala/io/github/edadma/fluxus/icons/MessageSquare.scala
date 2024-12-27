package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square", JSImport.Default)
private object MessageSquareIcon extends js.Array[js.Any]

def MessageSquare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareIcon, color, size)
