package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/table-of-contents", JSImport.Default)
private object TableOfContentsIcon extends js.Array[js.Any]

def TableOfContents(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TableOfContentsIcon, color, size)
