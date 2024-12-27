package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/subscript", JSImport.Default)
private object SubscriptIcon extends js.Array[js.Any]

def Subscript(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SubscriptIcon, color, size)
