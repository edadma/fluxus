package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tv-minimal-play", JSImport.Default)
private object TvMinimalPlayIcon extends js.Array[js.Any]

def TvMinimalPlay(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TvMinimalPlayIcon, color, size)
