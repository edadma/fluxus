package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/image-plus", JSImport.Default)
private object ImagePlusIcon extends js.Array[js.Any]

def ImagePlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImagePlusIcon, color, size)
