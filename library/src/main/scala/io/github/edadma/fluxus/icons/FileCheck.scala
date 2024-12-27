package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-check", JSImport.Default)
private object FileCheckIcon extends js.Array[js.Any]

def FileCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileCheckIcon, color, size)
