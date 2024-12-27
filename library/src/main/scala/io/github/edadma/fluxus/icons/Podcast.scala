package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/podcast", JSImport.Default)
private object PodcastIcon extends js.Array[js.Any]

def Podcast(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PodcastIcon, color, size)
