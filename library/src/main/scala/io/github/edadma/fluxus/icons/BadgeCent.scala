package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-cent", JSImport.Default)
private object BadgeCentIcon extends js.Array[js.Any]

def BadgeCent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeCentIcon, color, size)
