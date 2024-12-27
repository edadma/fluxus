package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tablet", JSImport.Default)
private object TabletIcon extends js.Array[js.Any]

def Tablet(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TabletIcon, color, size)
