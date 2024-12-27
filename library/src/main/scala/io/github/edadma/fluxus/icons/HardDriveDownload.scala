package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hard-drive-download", JSImport.Default)
private object HardDriveDownloadIcon extends js.Array[js.Any]

def HardDriveDownload(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HardDriveDownloadIcon, color, size)
