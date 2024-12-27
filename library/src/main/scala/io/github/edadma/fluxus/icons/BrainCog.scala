package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/brain-cog", JSImport.Default)
private object BrainCogIcon extends js.Array[js.Any]

def BrainCog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BrainCogIcon, color, size)
