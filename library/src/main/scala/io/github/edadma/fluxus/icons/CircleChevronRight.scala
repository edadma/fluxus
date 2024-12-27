package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-chevron-right", JSImport.Default)
private object CircleChevronRightIcon extends js.Array[js.Any]

def CircleChevronRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleChevronRightIcon, color, size)
