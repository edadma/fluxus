package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-chevron-up", JSImport.Default)
private object CircleChevronUpIcon extends js.Array[js.Any]

def CircleChevronUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleChevronUpIcon, color, size)
