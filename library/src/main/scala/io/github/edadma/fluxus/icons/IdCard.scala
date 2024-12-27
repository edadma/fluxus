package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/id-card", JSImport.Default)
private object IdCardIcon extends js.Array[js.Any]

def IdCard(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(IdCardIcon, color, size)
