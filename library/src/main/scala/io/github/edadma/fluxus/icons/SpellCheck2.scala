package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/spell-check-2", JSImport.Default)
private object SpellCheck2Icon extends js.Array[js.Any]

def SpellCheck2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SpellCheck2Icon, color, size)
