package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/history", JSImport.Default)
private object HistoryIcon extends js.Array[js.Any]

def History(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HistoryIcon, color, size)
