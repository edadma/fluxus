package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/glasses", JSImport.Default)
private object GlassesIcon extends js.Array[js.Any]

def Glasses(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GlassesIcon, color, size)
