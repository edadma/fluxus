package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tags", JSImport.Default)
private object TagsIcon extends js.Array[js.Any]

def Tags(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TagsIcon, color, size)
