package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/image-down", JSImport.Default)
private object ImageDownIcon extends js.Array[js.Any]

def ImageDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImageDownIcon, color, size)
