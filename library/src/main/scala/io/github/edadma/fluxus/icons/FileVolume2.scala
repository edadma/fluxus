package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-volume-2", JSImport.Default)
private object FileVolume2Icon extends js.Array[js.Any]

def FileVolume2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileVolume2Icon, color, size)