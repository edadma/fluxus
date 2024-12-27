package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-0-1", JSImport.Default)
private object ArrowDown01Icon extends js.Array[js.Any]

def ArrowDown01(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDown01Icon, color, size)
