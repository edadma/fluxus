package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panels-top-left", JSImport.Default)
private object PanelsTopLeftIcon extends js.Array[js.Any]

def PanelsTopLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelsTopLeftIcon, color, size)
