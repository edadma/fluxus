package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/syringe", JSImport.Default)
private object SyringeIcon extends js.Array[js.Any]

def Syringe(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SyringeIcon, color, size)
