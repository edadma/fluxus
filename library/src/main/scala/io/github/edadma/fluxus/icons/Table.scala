package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/table", JSImport.Default)
private object TableIcon extends js.Array[js.Any]

def Table(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TableIcon, color, size)
