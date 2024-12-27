package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/app-window", JSImport.Default)
private object AppWindowIcon extends js.Array[js.Any]

def AppWindow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AppWindowIcon, color, size)
