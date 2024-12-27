package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heading-2", JSImport.Default)
private object Heading2Icon extends js.Array[js.Any]

def Heading2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Heading2Icon, color, size)
