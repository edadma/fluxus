package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lamp-ceiling", JSImport.Default)
private object LampCeilingIcon extends js.Array[js.Any]

def LampCeiling(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LampCeilingIcon, color, size)
