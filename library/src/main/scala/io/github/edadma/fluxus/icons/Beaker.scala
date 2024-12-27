package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/beaker", JSImport.Default)
private object BeakerIcon extends js.Array[js.Any]

def Beaker(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BeakerIcon, color, size)
