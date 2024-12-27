package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/radical", JSImport.Default)
private object RadicalIcon extends js.Array[js.Any]

def Radical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RadicalIcon, color, size)
