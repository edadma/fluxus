package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-help", JSImport.Default)
private object CircleHelpIcon extends js.Array[js.Any]

def CircleHelp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleHelpIcon, color, size)
