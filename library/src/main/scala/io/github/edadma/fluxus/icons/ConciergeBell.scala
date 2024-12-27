package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/concierge-bell", JSImport.Default)
private object ConciergeBellIcon extends js.Array[js.Any]

def ConciergeBell(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ConciergeBellIcon, color, size)
