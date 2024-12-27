package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sun", JSImport.Default)
private object SunIcon extends js.Array[js.Any]

def Sun(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SunIcon, color, size)
