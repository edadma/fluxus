package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/candy", JSImport.Default)
private object CandyIcon extends js.Array[js.Any]

def Candy(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CandyIcon, color, size)
