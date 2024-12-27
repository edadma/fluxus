package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fire-extinguisher", JSImport.Default)
private object FireExtinguisherIcon extends js.Array[js.Any]

def FireExtinguisher(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FireExtinguisherIcon, color, size)
