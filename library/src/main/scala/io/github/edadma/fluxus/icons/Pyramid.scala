package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pyramid", JSImport.Default)
private object PyramidIcon extends js.Array[js.Any]

def Pyramid(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PyramidIcon, color, size)
