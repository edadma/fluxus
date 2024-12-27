package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-reply", JSImport.Default)
private object MessageCircleReplyIcon extends js.Array[js.Any]

def MessageCircleReply(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleReplyIcon, color, size)
