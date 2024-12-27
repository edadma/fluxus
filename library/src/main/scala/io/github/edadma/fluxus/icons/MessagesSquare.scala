package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/messages-square", JSImport.Default)
private object MessagesSquareIcon extends js.Array[js.Any]

def MessagesSquare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessagesSquareIcon, color, size)
