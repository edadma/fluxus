package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/facebook", JSImport.Default)
private object FacebookIcon extends js.Array[js.Any]

def Facebook(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FacebookIcon, color, size)
