package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/octagon-pause", JSImport.Default)
private object OctagonPauseIcon extends js.Array[js.Any]

def OctagonPause(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OctagonPauseIcon, color, size)
