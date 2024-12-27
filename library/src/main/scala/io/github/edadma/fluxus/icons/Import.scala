package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/import", JSImport.Default)
private object ImportIcon extends js.Array[js.Any]

def Import(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ImportIcon, color, size)
