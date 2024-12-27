package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/zap", JSImport.Default)
private object ZapIcon extends js.Array[js.Any]

def Zap(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ZapIcon, color, size)
