package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tally-2", JSImport.Default)
private object Tally2Icon extends js.Array[js.Any]

def Tally2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Tally2Icon, color, size)
