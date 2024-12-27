package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/earth", JSImport.Default)
private object EarthIcon extends js.Array[js.Any]

def Earth(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EarthIcon, color, size)
