package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/plane-landing", JSImport.Default)
private object PlaneLandingIcon extends js.Array[js.Any]

def PlaneLanding(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PlaneLandingIcon, color, size)
