package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/video", JSImport.Default)
private object VideoIcon extends js.Array[js.Any]

def Video(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VideoIcon, color, size)
