package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bean-off", JSImport.Default)
private object BeanOffIcon extends js.Array[js.Any]

def BeanOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BeanOffIcon, color, size)
