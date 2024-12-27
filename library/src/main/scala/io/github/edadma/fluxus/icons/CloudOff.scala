package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-off", JSImport.Default)
private object CloudOffIcon extends js.Array[js.Any]

def CloudOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudOffIcon, color, size)
