package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/loader-circle", JSImport.Default)
private object LoaderCircleIcon extends js.Array[js.Any]

def LoaderCircle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LoaderCircleIcon, color, size)
