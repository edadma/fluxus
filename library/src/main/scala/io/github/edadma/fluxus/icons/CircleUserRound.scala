package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-user-round", JSImport.Default)
private object CircleUserRoundIcon extends js.Array[js.Any]

def CircleUserRound(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleUserRoundIcon, color, size)
