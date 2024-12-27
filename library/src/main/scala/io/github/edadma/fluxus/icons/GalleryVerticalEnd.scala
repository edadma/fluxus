package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gallery-vertical-end", JSImport.Default)
private object GalleryVerticalEndIcon extends js.Array[js.Any]

def GalleryVerticalEnd(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GalleryVerticalEndIcon, color, size)
