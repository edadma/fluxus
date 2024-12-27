package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/feather", JSImport.Default)
private object FeatherIcon extends js.Array[js.Any]

def Feather(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FeatherIcon, color, size)
