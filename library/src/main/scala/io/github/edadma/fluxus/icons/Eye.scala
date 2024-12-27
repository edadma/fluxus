package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/eye", JSImport.Default)
private object EyeIcon extends js.Array[js.Any]

def Eye(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EyeIcon, color, size)
