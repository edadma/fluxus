package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/image-up", JSImport.Default)
private object ImageUpIcon extends js.Array[js.Any]

def ImageUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImageUpIcon, color, size)
