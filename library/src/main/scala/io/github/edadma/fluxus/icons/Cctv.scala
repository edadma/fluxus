package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cctv", JSImport.Default)
private object CctvIcon extends js.Array[js.Any]

def Cctv(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CctvIcon, color, size)
