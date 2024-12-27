package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pencil-ruler", JSImport.Default)
private object PencilRulerIcon extends js.Array[js.Any]

def PencilRuler(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PencilRulerIcon, color, size)
