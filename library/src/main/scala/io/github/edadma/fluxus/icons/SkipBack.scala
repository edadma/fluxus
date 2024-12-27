package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/skip-back", JSImport.Default)
private object SkipBackIcon extends js.Array[js.Any]

def SkipBack(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SkipBackIcon, color, size)
