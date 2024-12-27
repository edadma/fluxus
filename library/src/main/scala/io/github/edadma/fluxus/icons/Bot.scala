package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bot", JSImport.Default)
private object BotIcon extends js.Array[js.Any]

def Bot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BotIcon, color, size)
