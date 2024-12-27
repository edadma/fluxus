package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/scan-heart", JSImport.Default)
private object ScanHeartIcon extends js.Array[js.Any]

def ScanHeart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ScanHeartIcon, color, size)
