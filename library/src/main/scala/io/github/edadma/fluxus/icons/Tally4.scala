package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tally-4", JSImport.Default)
private object Tally4Icon extends js.Array[js.Any]

def Tally4(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Tally4Icon, color, size)
