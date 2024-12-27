package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/video-off", JSImport.Default)
private object VideoOffIcon extends js.Array[js.Any]

def VideoOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VideoOffIcon, color, size)
