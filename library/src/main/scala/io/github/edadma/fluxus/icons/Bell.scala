package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bell", JSImport.Default)
private object BellIcon extends js.Array[js.Any]

def Bell(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BellIcon, color, size)
