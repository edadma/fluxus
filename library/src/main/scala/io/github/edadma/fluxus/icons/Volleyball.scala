package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/volleyball", JSImport.Default)
private object VolleyballIcon extends js.Array[js.Any]

def Volleyball(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VolleyballIcon, color, size)