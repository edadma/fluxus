package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-video", JSImport.Default)
private object ListVideoIcon extends js.Array[js.Any]

def ListVideo(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListVideoIcon, color, size)
