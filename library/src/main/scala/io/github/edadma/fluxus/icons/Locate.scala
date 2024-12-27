package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/locate", JSImport.Default)
private object LocateIcon extends js.Array[js.Any]

def Locate(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LocateIcon, color, size)
