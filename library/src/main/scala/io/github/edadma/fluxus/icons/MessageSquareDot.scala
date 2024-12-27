package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-dot", JSImport.Default)
private object MessageSquareDotIcon extends js.Array[js.Any]

def MessageSquareDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareDotIcon, color, size)
