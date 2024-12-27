package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-diff", JSImport.Default)
private object MessageSquareDiffIcon extends js.Array[js.Any]

def MessageSquareDiff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareDiffIcon, color, size)
