package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hard-hat", JSImport.Default)
private object HardHatIcon extends js.Array[js.Any]

def HardHat(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HardHatIcon, color, size)
