package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/headphone-off", JSImport.Default)
private object HeadphoneOffIcon extends js.Array[js.Any]

def HeadphoneOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeadphoneOffIcon, color, size)
