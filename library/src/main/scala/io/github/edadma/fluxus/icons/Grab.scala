package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/grab", JSImport.Default)
private object GrabIcon extends js.Array[js.Any]

def Grab(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GrabIcon, color, size)
