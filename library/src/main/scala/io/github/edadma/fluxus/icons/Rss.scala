package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rss", JSImport.Default)
private object RssIcon extends js.Array[js.Any]

def Rss(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RssIcon, color, size)
