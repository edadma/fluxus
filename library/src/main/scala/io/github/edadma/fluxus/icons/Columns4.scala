package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/columns-4", JSImport.Default)
private object Columns4Icon extends js.Array[js.Any]

def Columns4(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Columns4Icon, color, size)
