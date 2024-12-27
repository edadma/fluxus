package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/squircle", JSImport.Default)
private object SquircleIcon extends js.Array[js.Any]

def Squircle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquircleIcon, color, size)
