package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chrome", JSImport.Default)
private object ChromeIcon extends js.Array[js.Any]

def Chrome(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChromeIcon, color, size)
