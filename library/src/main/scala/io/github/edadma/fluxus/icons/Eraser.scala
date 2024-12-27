package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/eraser", JSImport.Default)
private object EraserIcon extends js.Array[js.Any]

def Eraser(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EraserIcon, color, size)
