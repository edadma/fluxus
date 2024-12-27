package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-json-2", JSImport.Default)
private object FileJson2Icon extends js.Array[js.Any]

def FileJson2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileJson2Icon, color, size)
