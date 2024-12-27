package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/trophy", JSImport.Default)
private object TrophyIcon extends js.Array[js.Any]

def Trophy(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrophyIcon, color, size)
