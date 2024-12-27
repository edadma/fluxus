package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-z-a", JSImport.Default)
private object ArrowDownZAIcon extends js.Array[js.Any]

def ArrowDownZA(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownZAIcon, color, size)
