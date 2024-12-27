package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tag", JSImport.Default)
private object TagIcon extends js.Array[js.Any]

def Tag(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TagIcon, color, size)
