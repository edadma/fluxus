package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/plane", JSImport.Default)
private object PlaneIcon extends js.Array[js.Any]

def Plane(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PlaneIcon, color, size)
