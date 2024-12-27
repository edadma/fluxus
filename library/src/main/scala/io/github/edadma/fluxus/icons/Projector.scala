package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/projector", JSImport.Default)
private object ProjectorIcon extends js.Array[js.Any]

def Projector(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ProjectorIcon, color, size)
