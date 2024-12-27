package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-diff", JSImport.Default)
private object FileDiffIcon extends js.Array[js.Any]

def FileDiff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileDiffIcon, color, size)
