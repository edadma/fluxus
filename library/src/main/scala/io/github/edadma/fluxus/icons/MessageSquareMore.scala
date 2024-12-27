package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-more", JSImport.Default)
private object MessageSquareMoreIcon extends js.Array[js.Any]

def MessageSquareMore(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareMoreIcon, color, size)
