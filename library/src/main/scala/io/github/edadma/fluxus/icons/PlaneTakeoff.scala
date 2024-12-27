package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/plane-takeoff", JSImport.Default)
private object PlaneTakeoffIcon extends js.Array[js.Any]

def PlaneTakeoff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PlaneTakeoffIcon, color, size)
