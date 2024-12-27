package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cylinder", JSImport.Default)
private object CylinderIcon extends js.Array[js.Any]

def Cylinder(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CylinderIcon, color, size)
