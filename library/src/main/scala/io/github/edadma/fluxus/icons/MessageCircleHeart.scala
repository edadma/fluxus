package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-heart", JSImport.Default)
private object MessageCircleHeartIcon extends js.Array[js.Any]

def MessageCircleHeart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleHeartIcon, color, size)
