package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/package-search", JSImport.Default)
private object PackageSearchIcon extends js.Array[js.Any]

def PackageSearch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PackageSearchIcon, color, size)
