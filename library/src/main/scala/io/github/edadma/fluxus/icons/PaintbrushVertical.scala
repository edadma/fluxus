package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/paintbrush-vertical", JSImport.Default)
private object PaintbrushVerticalIcon extends js.Array[js.Any]

def PaintbrushVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PaintbrushVerticalIcon, color, size)
