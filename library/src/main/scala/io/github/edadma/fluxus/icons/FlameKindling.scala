package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flame-kindling", JSImport.Default)
private object FlameKindlingIcon extends js.Array[js.Any]

def FlameKindling(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlameKindlingIcon, color, size)
