package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-big-right-dash", JSImport.Default)
private object ArrowBigRightDashIcon extends js.Array[js.Any]

def ArrowBigRightDash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowBigRightDashIcon, color, size)
