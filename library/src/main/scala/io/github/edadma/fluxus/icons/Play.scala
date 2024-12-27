package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/play", JSImport.Default)
private object PlayIcon extends js.Array[js.Any]

def Play(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PlayIcon, color, size)
