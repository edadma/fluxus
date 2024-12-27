package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-spreadsheet", JSImport.Default)
private object FileSpreadsheetIcon extends js.Array[js.Any]

def FileSpreadsheet(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileSpreadsheetIcon, color, size)
