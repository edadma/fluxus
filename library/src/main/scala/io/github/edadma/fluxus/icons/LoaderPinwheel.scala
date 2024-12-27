package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/loader-pinwheel", JSImport.Default)
private object LoaderPinwheelIcon extends js.Array[js.Any]

def LoaderPinwheel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LoaderPinwheelIcon, color, size)
