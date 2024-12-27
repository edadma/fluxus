package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/component", JSImport.Default)
private object ComponentIcon extends js.Array[js.Any]

def Component(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ComponentIcon, color, size)
