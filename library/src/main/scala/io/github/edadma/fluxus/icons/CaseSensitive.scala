package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/case-sensitive", JSImport.Default)
private object CaseSensitiveIcon extends js.Array[js.Any]

def CaseSensitive(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CaseSensitiveIcon, color, size)
