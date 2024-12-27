package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/globe-lock", JSImport.Default)
private object GlobeLockIcon extends js.Array[js.Any]

def GlobeLock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GlobeLockIcon, color, size)
