package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/screen-share-off", JSImport.Default)
private object ScreenShareOffIcon extends js.Array[js.Any]

def ScreenShareOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScreenShareOffIcon, color, size)
