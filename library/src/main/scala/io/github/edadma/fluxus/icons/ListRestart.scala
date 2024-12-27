package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-restart", JSImport.Default)
private object ListRestartIcon extends js.Array[js.Any]

def ListRestart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListRestartIcon, color, size)
