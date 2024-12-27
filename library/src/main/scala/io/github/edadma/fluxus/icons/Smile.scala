package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/smile", JSImport.Default)
private object SmileIcon extends js.Array[js.Any]

def Smile(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SmileIcon, color, size)
