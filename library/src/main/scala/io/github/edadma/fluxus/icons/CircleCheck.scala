package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-check", JSImport.Default)
private object CircleCheckIcon extends js.Array[js.Any]

def CircleCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleCheckIcon, color, size)
