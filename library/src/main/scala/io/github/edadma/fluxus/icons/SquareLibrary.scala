package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-library", JSImport.Default)
private object SquareLibraryIcon extends js.Array[js.Any]

def SquareLibrary(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareLibraryIcon, color, size)
