package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/variable", JSImport.Default)
private object VariableIcon extends js.Array[js.Any]

def Variable(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VariableIcon, color, size)
