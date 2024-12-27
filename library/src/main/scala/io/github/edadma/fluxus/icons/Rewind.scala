package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rewind", JSImport.Default)
private object RewindIcon extends js.Array[js.Any]

def Rewind(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RewindIcon, color, size)
