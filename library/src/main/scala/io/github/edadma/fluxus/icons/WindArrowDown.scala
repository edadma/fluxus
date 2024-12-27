package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wind-arrow-down", JSImport.Default)
private object WindArrowDownIcon extends js.Array[js.Any]

def WindArrowDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WindArrowDownIcon, color, size)
