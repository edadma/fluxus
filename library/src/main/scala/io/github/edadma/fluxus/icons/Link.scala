package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/link", JSImport.Default)
private object LinkIcon extends js.Array[js.Any]

def Link(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LinkIcon, color, size)
