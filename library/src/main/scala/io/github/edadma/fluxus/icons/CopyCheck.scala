package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/copy-check", JSImport.Default)
private object CopyCheckIcon extends js.Array[js.Any]

def CopyCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CopyCheckIcon, color, size)
