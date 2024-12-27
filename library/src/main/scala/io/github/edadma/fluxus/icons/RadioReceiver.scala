package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/radio-receiver", JSImport.Default)
private object RadioReceiverIcon extends js.Array[js.Any]

def RadioReceiver(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RadioReceiverIcon, color, size)
