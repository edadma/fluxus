package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/briefcase-medical", JSImport.Default)
private object BriefcaseMedicalIcon extends js.Array[js.Any]

def BriefcaseMedical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BriefcaseMedicalIcon, color, size)
