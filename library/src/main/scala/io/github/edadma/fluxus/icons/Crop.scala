package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/crop", JSImport.Default)
private object CropIcon extends js.Array[js.Any]

def Crop(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CropIcon, color, size)
