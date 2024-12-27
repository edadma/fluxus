package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heading-4", JSImport.Default)
private object Heading4Icon extends js.Array[js.Any]

def Heading4(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Heading4Icon, color, size)
