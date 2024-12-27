package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/notebook-tabs", JSImport.Default)
private object NotebookTabsIcon extends js.Array[js.Any]

def NotebookTabs(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NotebookTabsIcon, color, size)
