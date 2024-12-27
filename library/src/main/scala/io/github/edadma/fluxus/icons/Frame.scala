package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/frame", JSImport.Default)
private object FrameIcon extends js.Array[js.Any]

def Frame(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FrameIcon, color, size)
