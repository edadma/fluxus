package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/replace", JSImport.Default)
private object ReplaceIcon extends js.Array[js.Any]

def Replace(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReplaceIcon, color, size)
