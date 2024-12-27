package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevrons-up", JSImport.Default)
private object ChevronsUpIcon extends js.Array[js.Any]

def ChevronsUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronsUpIcon, color, size)
