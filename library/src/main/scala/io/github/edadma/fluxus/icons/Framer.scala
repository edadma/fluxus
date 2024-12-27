package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/framer", JSImport.Default)
private object FramerIcon extends js.Array[js.Any]

def Framer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FramerIcon, color, size)
