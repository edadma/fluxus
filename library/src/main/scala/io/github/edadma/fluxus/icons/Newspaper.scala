package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/newspaper", JSImport.Default)
private object NewspaperIcon extends js.Array[js.Any]

def Newspaper(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NewspaperIcon, color, size)
