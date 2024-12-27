package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-video-2", JSImport.Default)
private object FileVideo2Icon extends js.Array[js.Any]

def FileVideo2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileVideo2Icon, color, size)
