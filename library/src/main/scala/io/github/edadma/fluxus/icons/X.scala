package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/x", JSImport.Default)
private object XIcon extends js.Array[js.Any]

def X(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(XIcon, color, size)
