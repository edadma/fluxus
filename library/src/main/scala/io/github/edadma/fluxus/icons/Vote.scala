package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/vote", JSImport.Default)
private object VoteIcon extends js.Array[js.Any]

def Vote(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VoteIcon, color, size)
