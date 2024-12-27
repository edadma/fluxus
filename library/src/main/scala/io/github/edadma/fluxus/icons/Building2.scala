package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/building-2", JSImport.Default)
private object Building2Icon extends js.Array[js.Any]

def Building2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Building2Icon, color, size)
