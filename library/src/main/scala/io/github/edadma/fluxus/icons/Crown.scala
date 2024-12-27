package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/crown", JSImport.Default)
private object CrownIcon extends js.Array[js.Any]

def Crown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CrownIcon, color, size)
