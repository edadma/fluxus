package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/popsicle", JSImport.Default)
private object PopsicleIcon extends js.Array[js.Any]

def Popsicle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PopsicleIcon, color, size)
