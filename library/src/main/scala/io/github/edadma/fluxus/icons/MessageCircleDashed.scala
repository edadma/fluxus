package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-dashed", JSImport.Default)
private object MessageCircleDashedIcon extends js.Array[js.Any]

def MessageCircleDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleDashedIcon, color, size)
