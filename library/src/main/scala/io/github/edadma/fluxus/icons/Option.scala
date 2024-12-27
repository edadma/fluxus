package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/option", JSImport.Default)
private object OptionIcon extends js.Array[js.Any]

def Option(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OptionIcon, color, size)
