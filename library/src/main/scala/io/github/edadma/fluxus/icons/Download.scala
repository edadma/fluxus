package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/download", JSImport.Default)
private object DownloadIcon extends js.Array[js.Any]

def Download(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DownloadIcon, color, size)
