package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/globe", JSImport.Default)
private object GlobeIcon extends js.Array[js.Any]

def Globe(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GlobeIcon, color, size)
