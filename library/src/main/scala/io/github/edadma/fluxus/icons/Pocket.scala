package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pocket", JSImport.Default)
private object PocketIcon extends js.Array[js.Any]

def Pocket(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PocketIcon, color, size)
