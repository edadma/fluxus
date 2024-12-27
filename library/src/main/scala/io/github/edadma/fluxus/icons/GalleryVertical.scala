package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gallery-vertical", JSImport.Default)
private object GalleryVerticalIcon extends js.Array[js.Any]

def GalleryVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GalleryVerticalIcon, color, size)
