package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/radio-tower", JSImport.Default)
private object RadioTowerIcon extends js.Array[js.Any]

def RadioTower(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RadioTowerIcon, color, size)
