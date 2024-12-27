package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/stretch-vertical", JSImport.Default)
private object StretchVerticalIcon extends js.Array[js.Any]

def StretchVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StretchVerticalIcon, color, size)
