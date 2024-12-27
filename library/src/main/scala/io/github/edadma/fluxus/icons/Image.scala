package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/image", JSImport.Default)
private object ImageIcon extends js.Array[js.Any]

def Image(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImageIcon, color, size)
