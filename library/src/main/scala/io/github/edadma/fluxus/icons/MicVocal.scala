package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mic-vocal", JSImport.Default)
private object MicVocalIcon extends js.Array[js.Any]

def MicVocal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MicVocalIcon, color, size)
