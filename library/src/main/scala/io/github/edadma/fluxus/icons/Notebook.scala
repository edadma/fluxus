package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/notebook", JSImport.Default)
private object NotebookIcon extends js.Array[js.Any]

def Notebook(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NotebookIcon, color, size)
