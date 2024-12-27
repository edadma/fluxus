package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bot-message-square", JSImport.Default)
private object BotMessageSquareIcon extends js.Array[js.Any]

def BotMessageSquare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BotMessageSquareIcon, color, size)
