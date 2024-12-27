package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/align-justify", JSImport.Default)
private object AlignJustifyIcon extends js.Array[js.Any]

def AlignJustify(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlignJustifyIcon, color, size)
