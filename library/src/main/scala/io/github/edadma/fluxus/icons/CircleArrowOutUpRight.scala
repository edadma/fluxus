package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-arrow-out-up-right", JSImport.Default)
private object CircleArrowOutUpRightIcon extends js.Array[js.Any]

def CircleArrowOutUpRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleArrowOutUpRightIcon, color, size)
