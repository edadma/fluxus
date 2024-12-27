package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pentagon", JSImport.Default)
private object PentagonIcon extends js.Array[js.Any]

def Pentagon(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PentagonIcon, color, size)
