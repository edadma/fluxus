package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-stop", JSImport.Default)
private object CircleStopIcon extends js.Array[js.Any]

def CircleStop(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleStopIcon, color, size)
