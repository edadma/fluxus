package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pen-tool", JSImport.Default)
private object PenToolIcon extends js.Array[js.Any]

def PenTool(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PenToolIcon, color, size)
