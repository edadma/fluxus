package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/disc-2", JSImport.Default)
private object Disc2Icon extends js.Array[js.Any]

def Disc2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Disc2Icon, color, size)
