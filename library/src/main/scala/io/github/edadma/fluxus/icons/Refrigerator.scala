package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/refrigerator", JSImport.Default)
private object RefrigeratorIcon extends js.Array[js.Any]

def Refrigerator(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RefrigeratorIcon, color, size)
