package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/asterisk", JSImport.Default)
private object AsteriskIcon extends js.Array[js.Any]

def Asterisk(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AsteriskIcon, color, size)
