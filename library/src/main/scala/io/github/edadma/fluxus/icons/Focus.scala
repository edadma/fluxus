package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/focus", JSImport.Default)
private object FocusIcon extends js.Array[js.Any]

def Focus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FocusIcon, color, size)
