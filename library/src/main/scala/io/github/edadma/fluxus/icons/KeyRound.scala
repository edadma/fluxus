package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/key-round", JSImport.Default)
private object KeyRoundIcon extends js.Array[js.Any]

def KeyRound(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(KeyRoundIcon, color, size)
