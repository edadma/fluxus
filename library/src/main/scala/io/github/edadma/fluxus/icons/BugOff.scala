package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bug-off", JSImport.Default)
private object BugOffIcon extends js.Array[js.Any]

def BugOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BugOffIcon, color, size)
