package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/image-play", JSImport.Default)
private object ImagePlayIcon extends js.Array[js.Any]

def ImagePlay(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImagePlayIcon, color, size)
