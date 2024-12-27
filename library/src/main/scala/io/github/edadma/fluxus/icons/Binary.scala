package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/binary", JSImport.Default)
private object BinaryIcon extends js.Array[js.Any]

def Binary(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BinaryIcon, color, size)
