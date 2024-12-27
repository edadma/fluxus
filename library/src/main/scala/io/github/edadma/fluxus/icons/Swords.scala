package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/swords", JSImport.Default)
private object SwordsIcon extends js.Array[js.Any]

def Swords(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SwordsIcon, color, size)
