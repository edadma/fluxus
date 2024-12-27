package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pilcrow-left", JSImport.Default)
private object PilcrowLeftIcon extends js.Array[js.Any]

def PilcrowLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PilcrowLeftIcon, color, size)
