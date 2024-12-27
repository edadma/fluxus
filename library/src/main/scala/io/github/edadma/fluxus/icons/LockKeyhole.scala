package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lock-keyhole", JSImport.Default)
private object LockKeyholeIcon extends js.Array[js.Any]

def LockKeyhole(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LockKeyholeIcon, color, size)
