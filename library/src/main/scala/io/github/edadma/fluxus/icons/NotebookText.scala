package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/notebook-text", JSImport.Default)
private object NotebookTextIcon extends js.Array[js.Any]

def NotebookText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NotebookTextIcon, color, size)
