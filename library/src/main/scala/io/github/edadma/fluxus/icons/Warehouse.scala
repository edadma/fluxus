package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/warehouse", JSImport.Default)
private object WarehouseIcon extends js.Array[js.Any]

def Warehouse(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WarehouseIcon, color, size)
