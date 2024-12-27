package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/disc", JSImport.Default)
private object DiscIcon extends js.Array[js.Any]

def Disc(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DiscIcon, color, size)
