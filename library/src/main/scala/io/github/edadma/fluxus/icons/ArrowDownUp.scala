package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-down-up", JSImport.Default)
private object ArrowDownUpIcon extends js.Array[js.Any]

def ArrowDownUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowDownUpIcon, color, size)
