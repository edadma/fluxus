package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/landmark", JSImport.Default)
private object LandmarkIcon extends js.Array[js.Any]

def Landmark(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LandmarkIcon, color, size)
