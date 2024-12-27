package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/columns-2", JSImport.Default)
private object Columns2Icon extends js.Array[js.Any]

def Columns2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Columns2Icon, color, size)
