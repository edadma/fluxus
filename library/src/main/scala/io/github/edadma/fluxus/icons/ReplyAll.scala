package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/reply-all", JSImport.Default)
private object ReplyAllIcon extends js.Array[js.Any]

def ReplyAll(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReplyAllIcon, color, size)
