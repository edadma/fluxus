package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lock-open", JSImport.Default)
private object LockOpenIcon extends js.Array[js.Any]

def LockOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LockOpenIcon, color, size)
