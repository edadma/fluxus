package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/disc-3", JSImport.Default)
private object Disc3Icon extends js.Array[js.Any]

def Disc3(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Disc3Icon, color, size)
