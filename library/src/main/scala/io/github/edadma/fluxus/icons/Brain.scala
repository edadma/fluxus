package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/brain", JSImport.Default)
private object BrainIcon extends js.Array[js.Any]

def Brain(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BrainIcon, color, size)