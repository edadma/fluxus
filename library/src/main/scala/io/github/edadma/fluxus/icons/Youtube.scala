package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/youtube", JSImport.Default)
private object YoutubeIcon extends js.Array[js.Any]

def Youtube(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(YoutubeIcon, color, size)
