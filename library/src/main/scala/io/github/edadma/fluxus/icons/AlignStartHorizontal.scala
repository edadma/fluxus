package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-start-horizontal", JSImport.Default)
private object AlignStartHorizontalIcon extends js.Array[js.Any]

def AlignStartHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignStartHorizontalIcon, color, size)
