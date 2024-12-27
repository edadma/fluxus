package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-play", JSImport.Default)
private object CirclePlayIcon extends js.Array[js.Any]

def CirclePlay(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CirclePlayIcon, color, size)
