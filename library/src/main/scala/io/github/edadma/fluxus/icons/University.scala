package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/university", JSImport.Default)
private object UniversityIcon extends js.Array[js.Any]

def University(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UniversityIcon, color, size)
