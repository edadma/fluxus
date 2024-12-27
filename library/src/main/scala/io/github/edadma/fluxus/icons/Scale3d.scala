package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scale-3d", JSImport.Default)
private object Scale3dIcon extends js.Array[js.Any]

def Scale3d(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Scale3dIcon, color, size)
