package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/database", JSImport.Default)
private object DatabaseIcon extends js.Array[js.Any]

def Database(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DatabaseIcon, color, size)
