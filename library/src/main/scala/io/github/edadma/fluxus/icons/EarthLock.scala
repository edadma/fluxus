package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/earth-lock", JSImport.Default)
private object EarthLockIcon extends js.Array[js.Any]

def EarthLock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EarthLockIcon, color, size)
