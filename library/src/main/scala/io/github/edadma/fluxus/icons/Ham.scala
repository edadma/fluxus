package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ham", JSImport.Default)
private object HamIcon extends js.Array[js.Any]

def Ham(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HamIcon, color, size)
