package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-plus", JSImport.Default)
private object BadgePlusIcon extends js.Array[js.Any]

def BadgePlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgePlusIcon, color, size)
