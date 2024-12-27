package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-slash", JSImport.Default)
private object CircleSlashIcon extends js.Array[js.Any]

def CircleSlash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleSlashIcon, color, size)
