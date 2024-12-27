package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-pound-sterling", JSImport.Default)
private object BadgePoundSterlingIcon extends js.Array[js.Any]

def BadgePoundSterling(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgePoundSterlingIcon, color, size)
