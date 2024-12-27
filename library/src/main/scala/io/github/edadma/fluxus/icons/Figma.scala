package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/figma", JSImport.Default)
private object FigmaIcon extends js.Array[js.Any]

def Figma(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FigmaIcon, color, size)
