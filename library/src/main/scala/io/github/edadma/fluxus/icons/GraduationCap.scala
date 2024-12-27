package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/graduation-cap", JSImport.Default)
private object GraduationCapIcon extends js.Array[js.Any]

def GraduationCap(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GraduationCapIcon, color, size)
