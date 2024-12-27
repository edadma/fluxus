package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flower-2", JSImport.Default)
private object Flower2Icon extends js.Array[js.Any]

def Flower2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Flower2Icon, color, size)
