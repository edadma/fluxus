package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/beef", JSImport.Default)
private object BeefIcon extends js.Array[js.Any]

def Beef(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BeefIcon, color, size)
