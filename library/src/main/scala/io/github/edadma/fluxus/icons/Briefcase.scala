package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/briefcase", JSImport.Default)
private object BriefcaseIcon extends js.Array[js.Any]

def Briefcase(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BriefcaseIcon, color, size)
