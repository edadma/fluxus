package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heart-pulse", JSImport.Default)
private object HeartPulseIcon extends js.Array[js.Any]

def HeartPulse(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeartPulseIcon, color, size)
