package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/twitter", JSImport.Default)
private object TwitterIcon extends js.Array[js.Any]

def Twitter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TwitterIcon, color, size)
