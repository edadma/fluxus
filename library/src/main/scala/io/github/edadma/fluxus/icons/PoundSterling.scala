package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pound-sterling", JSImport.Default)
private object PoundSterlingIcon extends js.Array[js.Any]

def PoundSterling(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PoundSterlingIcon, color, size)
