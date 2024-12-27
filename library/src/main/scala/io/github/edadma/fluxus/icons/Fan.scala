package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fan", JSImport.Default)
private object FanIcon extends js.Array[js.Any]

def Fan(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FanIcon, color, size)
