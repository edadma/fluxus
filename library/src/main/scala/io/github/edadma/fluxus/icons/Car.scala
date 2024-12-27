package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/car", JSImport.Default)
private object CarIcon extends js.Array[js.Any]

def Car(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CarIcon, color, size)
