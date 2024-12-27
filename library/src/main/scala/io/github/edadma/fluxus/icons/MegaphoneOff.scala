package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/megaphone-off", JSImport.Default)
private object MegaphoneOffIcon extends js.Array[js.Any]

def MegaphoneOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MegaphoneOffIcon, color, size)
