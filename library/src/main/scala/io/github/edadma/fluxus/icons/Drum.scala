package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/drum", JSImport.Default)
private object DrumIcon extends js.Array[js.Any]

def Drum(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DrumIcon, color, size)
