package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lock", JSImport.Default)
private object LockIcon extends js.Array[js.Any]

def Lock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LockIcon, color, size)
