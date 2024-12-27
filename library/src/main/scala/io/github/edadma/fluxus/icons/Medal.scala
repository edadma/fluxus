package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/medal", JSImport.Default)
private object MedalIcon extends js.Array[js.Any]

def Medal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MedalIcon, color, size)
