package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/whole-word", JSImport.Default)
private object WholeWordIcon extends js.Array[js.Any]

def WholeWord(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WholeWordIcon, color, size)
