package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/languages", JSImport.Default)
private object LanguagesIcon extends js.Array[js.Any]

def Languages(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LanguagesIcon, color, size)
