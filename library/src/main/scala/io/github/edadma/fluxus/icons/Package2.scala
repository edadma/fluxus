package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/package-2", JSImport.Default)
private object Package2Icon extends js.Array[js.Any]

def Package2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Package2Icon, color, size)
