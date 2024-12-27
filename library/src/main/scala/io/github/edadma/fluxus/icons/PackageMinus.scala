package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/package-minus", JSImport.Default)
private object PackageMinusIcon extends js.Array[js.Any]

def PackageMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PackageMinusIcon, color, size)
