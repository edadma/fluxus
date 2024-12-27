package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/group", JSImport.Default)
private object GroupIcon extends js.Array[js.Any]

def Group(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GroupIcon, color, size)
