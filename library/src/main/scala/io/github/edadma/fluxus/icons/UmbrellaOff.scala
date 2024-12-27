package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/umbrella-off", JSImport.Default)
private object UmbrellaOffIcon extends js.Array[js.Any]

def UmbrellaOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UmbrellaOffIcon, color, size)
