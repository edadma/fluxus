package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hard-drive-upload", JSImport.Default)
private object HardDriveUploadIcon extends js.Array[js.Any]

def HardDriveUpload(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HardDriveUploadIcon, color, size)
