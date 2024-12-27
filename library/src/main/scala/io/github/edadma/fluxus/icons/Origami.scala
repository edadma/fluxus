package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/origami", JSImport.Default)
private object OrigamiIcon extends js.Array[js.Any]

def Origami(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OrigamiIcon, color, size)
