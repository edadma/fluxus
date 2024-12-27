package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-x", JSImport.Default)
private object CircleXIcon extends js.Array[js.Any]

def CircleX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleXIcon, color, size)
