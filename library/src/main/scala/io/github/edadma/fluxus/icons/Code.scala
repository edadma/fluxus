package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/code", JSImport.Default)
private object CodeIcon extends js.Array[js.Any]

def Code(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CodeIcon, color, size)
