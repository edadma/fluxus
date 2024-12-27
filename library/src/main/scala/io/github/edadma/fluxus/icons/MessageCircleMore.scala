package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-more", JSImport.Default)
private object MessageCircleMoreIcon extends js.Array[js.Any]

def MessageCircleMore(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleMoreIcon, color, size)
