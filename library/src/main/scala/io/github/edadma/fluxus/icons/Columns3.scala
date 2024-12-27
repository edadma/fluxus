package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/columns-3", JSImport.Default)
private object Columns3Icon extends js.Array[js.Any]

def Columns3(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Columns3Icon, color, size)
