package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-image", JSImport.Default)
private object FileImageIcon extends js.Array[js.Any]

def FileImage(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileImageIcon, color, size)
