package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan-eye", JSImport.Default)
private object ScanEyeIcon extends js.Array[js.Any]

def ScanEye(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanEyeIcon, color, size)
