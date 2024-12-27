package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/diff", JSImport.Default)
private object DiffIcon extends js.Array[js.Any]

def Diff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DiffIcon, color, size)
