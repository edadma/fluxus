package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/logs", JSImport.Default)
private object LogsIcon extends js.Array[js.Any]

def Logs(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LogsIcon, color, size)
