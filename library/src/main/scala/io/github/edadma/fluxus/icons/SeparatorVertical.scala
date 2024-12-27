package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/separator-vertical", JSImport.Default)
private object SeparatorVerticalIcon extends js.Array[js.Any]

def SeparatorVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SeparatorVerticalIcon, color, size)
