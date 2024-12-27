package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/share", JSImport.Default)
private object ShareIcon extends js.Array[js.Any]

def Share(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShareIcon, color, size)
