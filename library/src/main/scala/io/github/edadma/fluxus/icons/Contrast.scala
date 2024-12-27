package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/contrast", JSImport.Default)
private object ContrastIcon extends js.Array[js.Any]

def Contrast(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ContrastIcon, color, size)
