package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/package", JSImport.Default)
private object PackageIcon extends js.Array[js.Any]

def Package(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PackageIcon, color, size)
