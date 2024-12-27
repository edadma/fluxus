package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/table-2", JSImport.Default)
private object Table2Icon extends js.Array[js.Any]

def Table2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Table2Icon, color, size)
