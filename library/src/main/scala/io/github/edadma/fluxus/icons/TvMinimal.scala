package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tv-minimal", JSImport.Default)
private object TvMinimalIcon extends js.Array[js.Any]

def TvMinimal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TvMinimalIcon, color, size)
