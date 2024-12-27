package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tractor", JSImport.Default)
private object TractorIcon extends js.Array[js.Any]

def Tractor(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TractorIcon, color, size)
