package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/notebook-pen", JSImport.Default)
private object NotebookPenIcon extends js.Array[js.Any]

def NotebookPen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NotebookPenIcon, color, size)
