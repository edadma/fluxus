package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/image-upscale", JSImport.Default)
private object ImageUpscaleIcon extends js.Array[js.Any]

def ImageUpscale(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImageUpscaleIcon, color, size)
