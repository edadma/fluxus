package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/egg-fried", JSImport.Default)
private object EggFriedIcon extends js.Array[js.Any]

def EggFried(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EggFriedIcon, color, size)
