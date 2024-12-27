package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/plug-2", JSImport.Default)
private object Plug2Icon extends js.Array[js.Any]

def Plug2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Plug2Icon, color, size)
