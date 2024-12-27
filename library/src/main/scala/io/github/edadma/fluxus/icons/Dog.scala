package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dog", JSImport.Default)
private object DogIcon extends js.Array[js.Any]

def Dog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DogIcon, color, size)
