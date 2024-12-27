package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/users-round", JSImport.Default)
private object UsersRoundIcon extends js.Array[js.Any]

def UsersRound(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UsersRoundIcon, color, size)
