package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/banana", JSImport.Default)
private object BananaIcon extends js.Array[js.Any]

def Banana(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BananaIcon, color, size)
