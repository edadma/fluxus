package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan-face", JSImport.Default)
private object ScanFaceIcon extends js.Array[js.Any]

def ScanFace(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanFaceIcon, color, size)
