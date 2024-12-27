package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-reply", JSImport.Default)
private object MessageSquareReplyIcon extends js.Array[js.Any]

def MessageSquareReply(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareReplyIcon, color, size)
