package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ribbon", JSImport.Default)
private object RibbonIcon extends js.Array[js.Any]

def Ribbon(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RibbonIcon, color, size)
