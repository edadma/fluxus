package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/regex", JSImport.Default)
private object RegexIcon extends js.Array[js.Any]

def Regex(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RegexIcon, color, size)
