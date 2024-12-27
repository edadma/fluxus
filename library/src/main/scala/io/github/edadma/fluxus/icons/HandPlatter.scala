package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hand-platter", JSImport.Default)
private object HandPlatterIcon extends js.Array[js.Any]

def HandPlatter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HandPlatterIcon, color, size)
