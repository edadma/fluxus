package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/webcam", JSImport.Default)
private object WebcamIcon extends js.Array[js.Any]

def Webcam(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WebcamIcon, color, size)
