package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cat", JSImport.Default)
private object CatIcon extends js.Array[js.Any]

def Cat(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CatIcon, color, size)
