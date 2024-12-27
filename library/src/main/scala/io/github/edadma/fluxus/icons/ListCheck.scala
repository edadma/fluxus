package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-check", JSImport.Default)
private object ListCheckIcon extends js.Array[js.Any]

def ListCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListCheckIcon, color, size)
