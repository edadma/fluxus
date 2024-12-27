package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hand-heart", JSImport.Default)
private object HandHeartIcon extends js.Array[js.Any]

def HandHeart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HandHeartIcon, color, size)
