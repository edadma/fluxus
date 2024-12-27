package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tv", JSImport.Default)
private object TvIcon extends js.Array[js.Any]

def Tv(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TvIcon, color, size)
