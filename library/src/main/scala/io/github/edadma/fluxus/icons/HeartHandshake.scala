package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heart-handshake", JSImport.Default)
private object HeartHandshakeIcon extends js.Array[js.Any]

def HeartHandshake(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeartHandshakeIcon, color, size)
