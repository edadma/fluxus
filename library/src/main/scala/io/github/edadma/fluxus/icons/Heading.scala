package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heading", JSImport.Default)
private object HeadingIcon extends js.Array[js.Any]

def Heading(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeadingIcon, color, size)
