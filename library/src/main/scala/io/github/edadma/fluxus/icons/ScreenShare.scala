package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/screen-share", JSImport.Default)
private object ScreenShareIcon extends js.Array[js.Any]

def ScreenShare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScreenShareIcon, color, size)
