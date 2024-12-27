package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tally-5", JSImport.Default)
private object Tally5Icon extends js.Array[js.Any]

def Tally5(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Tally5Icon, color, size)
