package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/anchor", JSImport.Default)
private object AnchorIcon extends js.Array[js.Any]

def Anchor(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AnchorIcon, color, size)
