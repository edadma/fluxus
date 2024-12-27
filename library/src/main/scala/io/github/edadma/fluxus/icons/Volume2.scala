package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/volume-2", JSImport.Default)
private object Volume2Icon extends js.Array[js.Any]

def Volume2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Volume2Icon, color, size)
