package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/package-plus", JSImport.Default)
private object PackagePlusIcon extends js.Array[js.Any]

def PackagePlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PackagePlusIcon, color, size)
