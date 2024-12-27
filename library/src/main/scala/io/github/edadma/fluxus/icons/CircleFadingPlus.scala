package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-fading-plus", JSImport.Default)
private object CircleFadingPlusIcon extends js.Array[js.Any]

def CircleFadingPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleFadingPlusIcon, color, size)
