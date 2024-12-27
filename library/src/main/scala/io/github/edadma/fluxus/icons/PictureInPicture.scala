package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/picture-in-picture", JSImport.Default)
private object PictureInPictureIcon extends js.Array[js.Any]

def PictureInPicture(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PictureInPictureIcon, color, size)
