package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-check-big", JSImport.Default)
private object CircleCheckBigIcon extends js.Array[js.Any]

def CircleCheckBig(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleCheckBigIcon, color, size)
