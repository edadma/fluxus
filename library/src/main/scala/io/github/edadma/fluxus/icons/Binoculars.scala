package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/binoculars", JSImport.Default)
private object BinocularsIcon extends js.Array[js.Any]

def Binoculars(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BinocularsIcon, color, size)
