package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-sun", JSImport.Default)
private object CloudSunIcon extends js.Array[js.Any]

def CloudSun(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudSunIcon, color, size)
