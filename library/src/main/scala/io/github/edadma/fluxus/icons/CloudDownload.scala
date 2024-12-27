package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-download", JSImport.Default)
private object CloudDownloadIcon extends js.Array[js.Any]

def CloudDownload(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudDownloadIcon, color, size)
