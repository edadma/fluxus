package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/blend", JSImport.Default)
private object BlendIcon extends js.Array[js.Any]

def Blend(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BlendIcon, color, size)
