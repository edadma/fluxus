package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/database-zap", JSImport.Default)
private object DatabaseZapIcon extends js.Array[js.Any]

def DatabaseZap(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DatabaseZapIcon, color, size)
