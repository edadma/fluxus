package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/laptop-minimal-check", JSImport.Default)
private object LaptopMinimalCheckIcon extends js.Array[js.Any]

def LaptopMinimalCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LaptopMinimalCheckIcon, color, size)
