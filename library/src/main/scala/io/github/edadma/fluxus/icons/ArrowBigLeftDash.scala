package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-big-left-dash", JSImport.Default)
private object ArrowBigLeftDashIcon extends js.Array[js.Any]

def ArrowBigLeftDash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowBigLeftDashIcon, color, size)
