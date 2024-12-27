package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-heart", JSImport.Default)
private object MessageSquareHeartIcon extends js.Array[js.Any]

def MessageSquareHeart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareHeartIcon, color, size)
