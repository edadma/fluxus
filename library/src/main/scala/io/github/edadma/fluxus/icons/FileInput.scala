package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-input", JSImport.Default)
private object FileInputIcon extends js.Array[js.Any]

def FileInput(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileInputIcon, color, size)
