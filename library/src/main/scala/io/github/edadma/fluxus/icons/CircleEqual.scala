package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-equal", JSImport.Default)
private object CircleEqualIcon extends js.Array[js.Any]

def CircleEqual(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleEqualIcon, color, size)
