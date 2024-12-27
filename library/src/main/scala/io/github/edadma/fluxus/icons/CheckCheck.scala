package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/check-check", JSImport.Default)
private object CheckCheckIcon extends js.Array[js.Any]

def CheckCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CheckCheckIcon, color, size)
