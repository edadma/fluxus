package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/settings-2", JSImport.Default)
private object Settings2Icon extends js.Array[js.Any]

def Settings2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Settings2Icon, color, size)
