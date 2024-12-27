package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/locate-fixed", JSImport.Default)
private object LocateFixedIcon extends js.Array[js.Any]

def LocateFixed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LocateFixedIcon, color, size)
