package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/briefcase-business", JSImport.Default)
private object BriefcaseBusinessIcon extends js.Array[js.Any]

def BriefcaseBusiness(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BriefcaseBusinessIcon, color, size)
