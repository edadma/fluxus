package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/banknote", JSImport.Default)
private object BanknoteIcon extends js.Array[js.Any]

def Banknote(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BanknoteIcon, color, size)
