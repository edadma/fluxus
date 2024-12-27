package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cpu", JSImport.Default)
private object CpuIcon extends js.Array[js.Any]

def Cpu(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CpuIcon, color, size)
