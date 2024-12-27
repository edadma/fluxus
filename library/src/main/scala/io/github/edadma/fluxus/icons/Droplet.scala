package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/droplet", JSImport.Default)
private object DropletIcon extends js.Array[js.Any]

def Droplet(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DropletIcon, color, size)
