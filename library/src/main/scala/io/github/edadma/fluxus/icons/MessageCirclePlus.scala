package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-plus", JSImport.Default)
private object MessageCirclePlusIcon extends js.Array[js.Any]

def MessageCirclePlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCirclePlusIcon, color, size)
