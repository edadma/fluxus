package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pinned", JSImport.Default)
private object MapPinnedIcon extends js.Array[js.Any]

def MapPinned(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinnedIcon, color, size)
