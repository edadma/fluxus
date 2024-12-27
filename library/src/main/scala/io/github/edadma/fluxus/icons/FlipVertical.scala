package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flip-vertical", JSImport.Default)
private object FlipVerticalIcon extends js.Array[js.Any]

def FlipVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlipVerticalIcon, color, size)
