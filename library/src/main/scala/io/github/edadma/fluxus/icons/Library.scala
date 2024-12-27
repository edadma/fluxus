package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/library", JSImport.Default)
private object LibraryIcon extends js.Array[js.Any]

def Library(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LibraryIcon, color, size)
