package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mouse", JSImport.Default)
private object MouseIcon extends js.Array[js.Any]

def Mouse(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MouseIcon, color, size)
