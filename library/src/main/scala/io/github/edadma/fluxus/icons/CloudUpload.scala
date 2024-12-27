package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-upload", JSImport.Default)
private object CloudUploadIcon extends js.Array[js.Any]

def CloudUpload(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudUploadIcon, color, size)
