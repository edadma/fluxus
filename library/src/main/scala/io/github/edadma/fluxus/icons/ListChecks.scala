package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-checks", JSImport.Default)
private object ListChecksIcon extends js.Array[js.Any]

def ListChecks(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListChecksIcon, color, size)
