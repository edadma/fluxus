package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/octagon-x", JSImport.Default)
private object OctagonXIcon extends js.Array[js.Any]

def OctagonX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OctagonXIcon, color, size)
