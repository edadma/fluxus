package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/laugh", JSImport.Default)
private object LaughIcon extends js.Array[js.Any]

def Laugh(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LaughIcon, color, size)
