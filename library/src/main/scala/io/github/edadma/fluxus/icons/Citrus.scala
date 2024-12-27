package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/citrus", JSImport.Default)
private object CitrusIcon extends js.Array[js.Any]

def Citrus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CitrusIcon, color, size)
