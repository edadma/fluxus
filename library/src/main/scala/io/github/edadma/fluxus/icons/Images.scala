package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/images", JSImport.Default)
private object ImagesIcon extends js.Array[js.Any]

def Images(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImagesIcon, color, size)
