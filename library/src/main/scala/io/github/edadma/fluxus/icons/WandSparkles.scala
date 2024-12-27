package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wand-sparkles", JSImport.Default)
private object WandSparklesIcon extends js.Array[js.Any]

def WandSparkles(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WandSparklesIcon, color, size)
