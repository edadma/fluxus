package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-user", JSImport.Default)
private object CircleUserIcon extends js.Array[js.Any]

def CircleUser(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleUserIcon, color, size)
