package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pilcrow-right", JSImport.Default)
private object PilcrowRightIcon extends js.Array[js.Any]

def PilcrowRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PilcrowRightIcon, color, size)
