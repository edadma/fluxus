package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/apple", JSImport.Default)
private object AppleIcon extends js.Array[js.Any]

def Apple(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AppleIcon, color, size)
