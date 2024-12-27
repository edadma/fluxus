package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/handshake", JSImport.Default)
private object HandshakeIcon extends js.Array[js.Any]

def Handshake(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HandshakeIcon, color, size)
