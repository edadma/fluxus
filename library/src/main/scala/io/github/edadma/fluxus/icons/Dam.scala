package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dam", JSImport.Default)
private object DamIcon extends js.Array[js.Any]

def Dam(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DamIcon, color, size)
