package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/repeat-1", JSImport.Default)
private object Repeat1Icon extends js.Array[js.Any]

def Repeat1(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Repeat1Icon, color, size)
