package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/merge", JSImport.Default)
private object MergeIcon extends js.Array[js.Any]

def Merge(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MergeIcon, color, size)
