package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-text", JSImport.Default)
private object FileTextIcon extends js.Array[js.Any]

def FileText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileTextIcon, color, size)