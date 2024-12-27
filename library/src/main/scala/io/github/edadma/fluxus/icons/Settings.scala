package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/settings", JSImport.Default)
private object SettingsIcon extends js.Array[js.Any]

def Settings(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SettingsIcon, color, size)
