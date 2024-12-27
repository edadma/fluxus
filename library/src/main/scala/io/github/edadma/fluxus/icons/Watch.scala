package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/watch", JSImport.Default)
private object WatchIcon extends js.Array[js.Any]

def Watch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WatchIcon, color, size)
