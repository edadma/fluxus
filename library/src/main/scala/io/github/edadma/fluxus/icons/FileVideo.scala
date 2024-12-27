package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-video", JSImport.Default)
private object FileVideoIcon extends js.Array[js.Any]

def FileVideo(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileVideoIcon, color, size)
