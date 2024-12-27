package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-warning", JSImport.Default)
private object MessageCircleWarningIcon extends js.Array[js.Any]

def MessageCircleWarning(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleWarningIcon, color, size)
