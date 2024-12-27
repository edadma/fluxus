package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/table-properties", JSImport.Default)
private object TablePropertiesIcon extends js.Array[js.Any]

def TableProperties(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TablePropertiesIcon, color, size)
