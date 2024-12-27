package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lock-keyhole-open", JSImport.Default)
private object LockKeyholeOpenIcon extends js.Array[js.Any]

def LockKeyholeOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LockKeyholeOpenIcon, color, size)
