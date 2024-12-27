package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fish", JSImport.Default)
private object FishIcon extends js.Array[js.Any]

def Fish(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FishIcon, color, size)
