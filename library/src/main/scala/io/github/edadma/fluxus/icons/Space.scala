package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/space", JSImport.Default)
private object SpaceIcon extends js.Array[js.Any]

def Space(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SpaceIcon, color, size)
