package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/picture-in-picture-2", JSImport.Default)
private object PictureInPicture2Icon extends js.Array[js.Any]

def PictureInPicture2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PictureInPicture2Icon, color, size)
