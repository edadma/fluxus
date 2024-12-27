package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/minimize-2", JSImport.Default)
private object Minimize2Icon extends js.Array[js.Any]

def Minimize2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Minimize2Icon, color, size)
