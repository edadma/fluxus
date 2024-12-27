package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shell", JSImport.Default)
private object ShellIcon extends js.Array[js.Any]

def Shell(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShellIcon, color, size)
