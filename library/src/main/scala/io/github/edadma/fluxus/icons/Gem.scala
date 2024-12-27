package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gem", JSImport.Default)
private object GemIcon extends js.Array[js.Any]

def Gem(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GemIcon, color, size)
