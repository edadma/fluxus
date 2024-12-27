package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/soup", JSImport.Default)
private object SoupIcon extends js.Array[js.Any]

def Soup(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SoupIcon, color, size)
