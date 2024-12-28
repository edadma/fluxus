package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/monitor-smartphone", JSImport.Default)
private object MonitorSmartphoneIcon extends js.Array[js.Any]

def MonitorSmartphone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MonitorSmartphoneIcon, color, size)