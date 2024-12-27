package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/view", JSImport.Default)
private object ViewIcon extends js.Array[js.Any]

def View(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ViewIcon, color, size)
