package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-chevron-down", JSImport.Default)
private object CircleChevronDownIcon extends js.Array[js.Any]

def CircleChevronDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleChevronDownIcon, color, size)
