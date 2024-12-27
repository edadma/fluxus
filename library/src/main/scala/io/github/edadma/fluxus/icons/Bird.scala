package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bird", JSImport.Default)
private object BirdIcon extends js.Array[js.Any]

def Bird(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BirdIcon, color, size)
