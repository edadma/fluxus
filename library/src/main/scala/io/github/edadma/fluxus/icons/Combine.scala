package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/combine", JSImport.Default)
private object CombineIcon extends js.Array[js.Any]

def Combine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CombineIcon, color, size)
