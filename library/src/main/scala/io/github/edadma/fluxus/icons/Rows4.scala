package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rows-4", JSImport.Default)
private object Rows4Icon extends js.Array[js.Any]

def Rows4(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Rows4Icon, color, size)
