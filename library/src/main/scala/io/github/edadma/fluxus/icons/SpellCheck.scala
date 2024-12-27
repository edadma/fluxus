package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/spell-check", JSImport.Default)
private object SpellCheckIcon extends js.Array[js.Any]

def SpellCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SpellCheckIcon, color, size)
