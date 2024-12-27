package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-axis-3d", JSImport.Default)
private object FileAxis3dIcon extends js.Array[js.Any]

def FileAxis3d(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileAxis3dIcon, color, size)
