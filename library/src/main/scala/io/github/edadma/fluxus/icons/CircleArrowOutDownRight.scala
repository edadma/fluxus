package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-arrow-out-down-right", JSImport.Default)
private object CircleArrowOutDownRightIcon extends js.Array[js.Any]

def CircleArrowOutDownRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleArrowOutDownRightIcon, color, size)
