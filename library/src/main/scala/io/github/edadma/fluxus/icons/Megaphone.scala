package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/megaphone", JSImport.Default)
private object MegaphoneIcon extends js.Array[js.Any]

def Megaphone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MegaphoneIcon, color, size)
