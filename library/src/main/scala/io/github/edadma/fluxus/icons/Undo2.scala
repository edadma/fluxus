package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/undo-2", JSImport.Default)
private object Undo2Icon extends js.Array[js.Any]

def Undo2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Undo2Icon, color, size)
