package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-arrow-down", JSImport.Default)
private object CircleArrowDownIcon extends js.Array[js.Any]

def CircleArrowDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleArrowDownIcon, color, size)
