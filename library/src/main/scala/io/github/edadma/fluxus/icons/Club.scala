package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/club", JSImport.Default)
private object ClubIcon extends js.Array[js.Any]

def Club(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClubIcon, color, size)
