package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flag", JSImport.Default)
private object FlagIcon extends js.Array[js.Any]

def Flag(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlagIcon, color, size)
