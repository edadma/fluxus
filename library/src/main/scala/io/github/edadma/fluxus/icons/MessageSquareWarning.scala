package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-warning", JSImport.Default)
private object MessageSquareWarningIcon extends js.Array[js.Any]

def MessageSquareWarning(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquareWarningIcon, color, size)
