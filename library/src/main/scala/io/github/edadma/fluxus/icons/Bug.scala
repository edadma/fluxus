package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bug", JSImport.Default)
private object BugIcon extends js.Array[js.Any]

def Bug(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BugIcon, color, size)
