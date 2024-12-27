package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shower-head", JSImport.Default)
private object ShowerHeadIcon extends js.Array[js.Any]

def ShowerHead(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShowerHeadIcon, color, size)
