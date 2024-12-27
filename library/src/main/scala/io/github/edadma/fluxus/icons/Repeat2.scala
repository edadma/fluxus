package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/repeat-2", JSImport.Default)
private object Repeat2Icon extends js.Array[js.Any]

def Repeat2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Repeat2Icon, color, size)
