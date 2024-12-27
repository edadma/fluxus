package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/layers-2", JSImport.Default)
private object Layers2Icon extends js.Array[js.Any]

def Layers2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Layers2Icon, color, size)
