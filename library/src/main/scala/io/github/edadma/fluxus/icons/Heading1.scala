package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heading-1", JSImport.Default)
private object Heading1Icon extends js.Array[js.Any]

def Heading1(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Heading1Icon, color, size)
