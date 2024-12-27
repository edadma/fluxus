package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-snow", JSImport.Default)
private object CloudSnowIcon extends js.Array[js.Any]

def CloudSnow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudSnowIcon, color, size)
