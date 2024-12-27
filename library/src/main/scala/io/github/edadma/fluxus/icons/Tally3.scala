package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tally-3", JSImport.Default)
private object Tally3Icon extends js.Array[js.Any]

def Tally3(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Tally3Icon, color, size)
