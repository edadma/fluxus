package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-big-up-dash", JSImport.Default)
private object ArrowBigUpDashIcon extends js.Array[js.Any]

def ArrowBigUpDash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowBigUpDashIcon, color, size)
