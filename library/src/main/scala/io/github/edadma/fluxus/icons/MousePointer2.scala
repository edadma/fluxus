package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mouse-pointer-2", JSImport.Default)
private object MousePointer2Icon extends js.Array[js.Any]

def MousePointer2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MousePointer2Icon, color, size)
