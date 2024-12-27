package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/braces", JSImport.Default)
private object BracesIcon extends js.Array[js.Any]

def Braces(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BracesIcon, color, size)
