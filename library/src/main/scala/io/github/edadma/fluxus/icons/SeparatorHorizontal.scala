package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/separator-horizontal", JSImport.Default)
private object SeparatorHorizontalIcon extends js.Array[js.Any]

def SeparatorHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SeparatorHorizontalIcon, color, size)
