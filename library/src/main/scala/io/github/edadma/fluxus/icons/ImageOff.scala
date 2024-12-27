package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/image-off", JSImport.Default)
private object ImageOffIcon extends js.Array[js.Any]

def ImageOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImageOffIcon, color, size)
