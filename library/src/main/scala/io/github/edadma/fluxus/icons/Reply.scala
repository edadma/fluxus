package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/reply", JSImport.Default)
private object ReplyIcon extends js.Array[js.Any]

def Reply(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReplyIcon, color, size)
