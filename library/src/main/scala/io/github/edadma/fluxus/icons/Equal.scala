package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/equal", JSImport.Default)
private object EqualIcon extends js.Array[js.Any]

def Equal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EqualIcon, color, size)
