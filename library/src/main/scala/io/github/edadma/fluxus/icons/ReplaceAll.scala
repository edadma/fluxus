package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/replace-all", JSImport.Default)
private object ReplaceAllIcon extends js.Array[js.Any]

def ReplaceAll(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ReplaceAllIcon, color, size)
