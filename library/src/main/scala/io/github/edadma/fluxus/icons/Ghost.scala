package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ghost", JSImport.Default)
private object GhostIcon extends js.Array[js.Any]

def Ghost(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GhostIcon, color, size)
