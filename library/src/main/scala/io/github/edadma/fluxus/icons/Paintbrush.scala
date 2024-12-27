package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/paintbrush", JSImport.Default)
private object PaintbrushIcon extends js.Array[js.Any]

def Paintbrush(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PaintbrushIcon, color, size)
