package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shuffle", JSImport.Default)
private object ShuffleIcon extends js.Array[js.Any]

def Shuffle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShuffleIcon, color, size)
