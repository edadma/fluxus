package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/container", JSImport.Default)
private object ContainerIcon extends js.Array[js.Any]

def Container(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ContainerIcon, color, size)
