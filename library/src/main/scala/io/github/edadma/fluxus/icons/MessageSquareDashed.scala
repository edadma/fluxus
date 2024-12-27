package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-dashed", JSImport.Default)
private object MessageSquareDashedIcon extends js.Array[js.Any]

def MessageSquareDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareDashedIcon, color, size)
