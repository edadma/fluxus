package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/info", JSImport.Default)
private object InfoIcon extends js.Array[js.Any]

def Info(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(InfoIcon, color, size)
