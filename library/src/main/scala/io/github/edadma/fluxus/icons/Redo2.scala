package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/redo-2", JSImport.Default)
private object Redo2Icon extends js.Array[js.Any]

def Redo2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Redo2Icon, color, size)
