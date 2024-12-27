package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/tower-control", JSImport.Default)
private object TowerControlIcon extends js.Array[js.Any]

def TowerControl(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TowerControlIcon, color, size)
