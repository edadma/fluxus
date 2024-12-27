package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-arrow-right", JSImport.Default)
private object CircleArrowRightIcon extends js.Array[js.Any]

def CircleArrowRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleArrowRightIcon, color, size)
