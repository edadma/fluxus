package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/loader", JSImport.Default)
private object LoaderIcon extends js.Array[js.Any]

def Loader(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LoaderIcon, color, size)
