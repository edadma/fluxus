package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-text", JSImport.Default)
private object MessageSquareTextIcon extends js.Array[js.Any]

def MessageSquareText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareTextIcon, color, size)
