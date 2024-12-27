package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/drafting-compass", JSImport.Default)
private object DraftingCompassIcon extends js.Array[js.Any]

def DraftingCompass(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DraftingCompassIcon, color, size)
