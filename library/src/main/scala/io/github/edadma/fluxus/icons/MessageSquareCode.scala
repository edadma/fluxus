package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-code", JSImport.Default)
private object MessageSquareCodeIcon extends js.Array[js.Any]

def MessageSquareCode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareCodeIcon, color, size)