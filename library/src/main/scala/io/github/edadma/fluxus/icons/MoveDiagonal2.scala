package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-diagonal-2", JSImport.Default)
private object MoveDiagonal2Icon extends js.Array[js.Any]

def MoveDiagonal2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveDiagonal2Icon, color, size)
