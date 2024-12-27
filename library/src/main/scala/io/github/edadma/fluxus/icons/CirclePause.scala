package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-pause", JSImport.Default)
private object CirclePauseIcon extends js.Array[js.Any]

def CirclePause(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CirclePauseIcon, color, size)
