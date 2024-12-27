package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/house-plus", JSImport.Default)
private object HousePlusIcon extends js.Array[js.Any]

def HousePlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HousePlusIcon, color, size)
