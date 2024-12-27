package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-big-down-dash", JSImport.Default)
private object ArrowBigDownDashIcon extends js.Array[js.Any]

def ArrowBigDownDash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowBigDownDashIcon, color, size)
