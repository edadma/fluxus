package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flip-horizontal-2", JSImport.Default)
private object FlipHorizontal2Icon extends js.Array[js.Any]

def FlipHorizontal2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlipHorizontal2Icon, color, size)
