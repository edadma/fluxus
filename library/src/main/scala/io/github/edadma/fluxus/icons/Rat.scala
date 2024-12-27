package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rat", JSImport.Default)
private object RatIcon extends js.Array[js.Any]

def Rat(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RatIcon, color, size)
