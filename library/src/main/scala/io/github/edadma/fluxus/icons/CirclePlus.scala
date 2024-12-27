package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-plus", JSImport.Default)
private object CirclePlusIcon extends js.Array[js.Any]

def CirclePlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CirclePlusIcon, color, size)
