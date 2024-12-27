package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/route", JSImport.Default)
private object RouteIcon extends js.Array[js.Any]

def Route(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RouteIcon, color, size)
