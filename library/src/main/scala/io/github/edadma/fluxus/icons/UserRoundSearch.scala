package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-round-search", JSImport.Default)
private object UserRoundSearchIcon extends js.Array[js.Any]

def UserRoundSearch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserRoundSearchIcon, color, size)
