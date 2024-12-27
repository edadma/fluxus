package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/app-window-mac", JSImport.Default)
private object AppWindowMacIcon extends js.Array[js.Any]

def AppWindowMac(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AppWindowMacIcon, color, size)
