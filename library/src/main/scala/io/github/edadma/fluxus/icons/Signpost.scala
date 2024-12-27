package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/signpost", JSImport.Default)
private object SignpostIcon extends js.Array[js.Any]

def Signpost(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SignpostIcon, color, size)
