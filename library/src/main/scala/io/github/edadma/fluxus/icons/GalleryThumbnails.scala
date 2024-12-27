package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gallery-thumbnails", JSImport.Default)
private object GalleryThumbnailsIcon extends js.Array[js.Any]

def GalleryThumbnails(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GalleryThumbnailsIcon, color, size)
