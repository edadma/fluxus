package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-output", JSImport.Default)
private object FileOutputIcon extends js.Array[js.Any]

def FileOutput(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileOutputIcon, color, size)
