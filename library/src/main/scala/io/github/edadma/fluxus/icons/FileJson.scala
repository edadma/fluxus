package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-json", JSImport.Default)
private object FileJsonIcon extends js.Array[js.Any]

def FileJson(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileJsonIcon, color, size)
