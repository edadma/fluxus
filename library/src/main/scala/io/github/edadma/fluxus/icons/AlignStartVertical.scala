package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-start-vertical", JSImport.Default)
private object AlignStartVerticalIcon extends js.Array[js.Any]

def AlignStartVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignStartVerticalIcon, color, size)
