package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/route-off", JSImport.Default)
private object RouteOffIcon extends js.Array[js.Any]

def RouteOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RouteOffIcon, color, size)
