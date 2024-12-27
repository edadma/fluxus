package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/superscript", JSImport.Default)
private object SuperscriptIcon extends js.Array[js.Any]

def Superscript(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SuperscriptIcon, color, size)
