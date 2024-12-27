package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mountain", JSImport.Default)
private object MountainIcon extends js.Array[js.Any]

def Mountain(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MountainIcon, color, size)
