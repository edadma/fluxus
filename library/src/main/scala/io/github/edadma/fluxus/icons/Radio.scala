package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/radio", JSImport.Default)
private object RadioIcon extends js.Array[js.Any]

def Radio(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RadioIcon, color, size)
