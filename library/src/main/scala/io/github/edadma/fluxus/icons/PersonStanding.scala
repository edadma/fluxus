package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/person-standing", JSImport.Default)
private object PersonStandingIcon extends js.Array[js.Any]

def PersonStanding(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PersonStandingIcon, color, size)
