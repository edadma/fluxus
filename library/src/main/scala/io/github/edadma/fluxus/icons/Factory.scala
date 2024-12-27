package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/factory", JSImport.Default)
private object FactoryIcon extends js.Array[js.Any]

def Factory(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FactoryIcon, color, size)
