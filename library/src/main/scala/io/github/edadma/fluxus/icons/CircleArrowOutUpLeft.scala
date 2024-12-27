package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-arrow-out-up-left", JSImport.Default)
private object CircleArrowOutUpLeftIcon extends js.Array[js.Any]

def CircleArrowOutUpLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleArrowOutUpLeftIcon, color, size)
