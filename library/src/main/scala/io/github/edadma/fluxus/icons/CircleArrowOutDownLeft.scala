package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-arrow-out-down-left", JSImport.Default)
private object CircleArrowOutDownLeftIcon extends js.Array[js.Any]

def CircleArrowOutDownLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleArrowOutDownLeftIcon, color, size)
