package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-compare", JSImport.Default)
private object GitCompareIcon extends js.Array[js.Any]

def GitCompare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitCompareIcon, color, size)
