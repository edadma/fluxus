package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gallery-horizontal", JSImport.Default)
private object GalleryHorizontalIcon extends js.Array[js.Any]

def GalleryHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GalleryHorizontalIcon, color, size)
