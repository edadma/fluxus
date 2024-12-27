package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tram-front", JSImport.Default)
private object TramFrontIcon extends js.Array[js.Any]

def TramFront(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TramFrontIcon, color, size)
