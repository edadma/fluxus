package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sticker", JSImport.Default)
private object StickerIcon extends js.Array[js.Any]

def Sticker(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StickerIcon, color, size)
