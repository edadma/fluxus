package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-vertical-space-around", JSImport.Default)
private object AlignVerticalSpaceAroundIcon extends js.Array[js.Any]

def AlignVerticalSpaceAround(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignVerticalSpaceAroundIcon, color, size)
