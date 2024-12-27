package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-square-plus", JSImport.Default)
private object MessageSquarePlusIcon extends js.Array[js.Any]

def MessageSquarePlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageSquarePlusIcon, color, size)
