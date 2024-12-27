package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/videotape", JSImport.Default)
private object VideotapeIcon extends js.Array[js.Any]

def Videotape(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VideotapeIcon, color, size)
