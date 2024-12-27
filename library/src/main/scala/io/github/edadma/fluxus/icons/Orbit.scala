package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/orbit", JSImport.Default)
private object OrbitIcon extends js.Array[js.Any]

def Orbit(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(OrbitIcon, color, size)
