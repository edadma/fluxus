package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scale", JSImport.Default)
private object ScaleIcon extends js.Array[js.Any]

def Scale(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScaleIcon, color, size)
