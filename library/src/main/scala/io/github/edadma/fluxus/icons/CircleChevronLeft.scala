package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-chevron-left", JSImport.Default)
private object CircleChevronLeftIcon extends js.Array[js.Any]

def CircleChevronLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleChevronLeftIcon, color, size)
