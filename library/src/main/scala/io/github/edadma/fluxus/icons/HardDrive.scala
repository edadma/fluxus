package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hard-drive", JSImport.Default)
private object HardDriveIcon extends js.Array[js.Any]

def HardDrive(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HardDriveIcon, color, size)
