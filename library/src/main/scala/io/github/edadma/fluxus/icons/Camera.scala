package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/camera", JSImport.Default)
private object CameraIcon extends js.Array[js.Any]

def Camera(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CameraIcon, color, size)
