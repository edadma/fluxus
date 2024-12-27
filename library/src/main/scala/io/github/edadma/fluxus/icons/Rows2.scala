package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rows-2", JSImport.Default)
private object Rows2Icon extends js.Array[js.Any]

def Rows2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Rows2Icon, color, size)
