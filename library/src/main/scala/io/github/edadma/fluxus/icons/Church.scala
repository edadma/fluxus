package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/church", JSImport.Default)
private object ChurchIcon extends js.Array[js.Any]

def Church(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChurchIcon, color, size)
