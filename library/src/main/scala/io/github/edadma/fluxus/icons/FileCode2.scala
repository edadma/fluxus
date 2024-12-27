package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-code-2", JSImport.Default)
private object FileCode2Icon extends js.Array[js.Any]

def FileCode2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileCode2Icon, color, size)
