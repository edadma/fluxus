package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/presentation", JSImport.Default)
private object PresentationIcon extends js.Array[js.Any]

def Presentation(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PresentationIcon, color, size)
