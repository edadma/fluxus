package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/case-lower", JSImport.Default)
private object CaseLowerIcon extends js.Array[js.Any]

def CaseLower(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CaseLowerIcon, color, size)
