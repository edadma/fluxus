package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/signpost-big", JSImport.Default)
private object SignpostBigIcon extends js.Array[js.Any]

def SignpostBig(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SignpostBigIcon, color, size)
