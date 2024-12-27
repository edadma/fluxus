package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/at-sign", JSImport.Default)
private object AtSignIcon extends js.Array[js.Any]

def AtSign(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AtSignIcon, color, size)
