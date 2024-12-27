package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/package-x", JSImport.Default)
private object PackageXIcon extends js.Array[js.Any]

def PackageX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PackageXIcon, color, size)
