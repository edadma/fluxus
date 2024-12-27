package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-compare-arrows", JSImport.Default)
private object GitCompareArrowsIcon extends js.Array[js.Any]

def GitCompareArrows(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitCompareArrowsIcon, color, size)
