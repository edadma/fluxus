package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hospital", JSImport.Default)
private object HospitalIcon extends js.Array[js.Any]

def Hospital(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HospitalIcon, color, size)
