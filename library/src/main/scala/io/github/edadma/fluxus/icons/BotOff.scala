package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bot-off", JSImport.Default)
private object BotOffIcon extends js.Array[js.Any]

def BotOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BotOffIcon, color, size)
