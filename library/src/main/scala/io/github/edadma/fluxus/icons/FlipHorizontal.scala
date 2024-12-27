package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flip-horizontal", JSImport.Default)
private object FlipHorizontalIcon extends js.Array[js.Any]

def FlipHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlipHorizontalIcon, color, size)
