package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mic", JSImport.Default)
private object MicIcon extends js.Array[js.Any]

def Mic(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MicIcon, color, size)
