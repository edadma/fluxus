package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/unlink", JSImport.Default)
private object UnlinkIcon extends js.Array[js.Any]

def Unlink(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UnlinkIcon, color, size)
