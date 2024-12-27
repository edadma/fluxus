package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/package-check", JSImport.Default)
private object PackageCheckIcon extends js.Array[js.Any]

def PackageCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PackageCheckIcon, color, size)
