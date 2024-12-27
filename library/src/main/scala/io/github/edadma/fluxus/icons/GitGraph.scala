package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-graph", JSImport.Default)
private object GitGraphIcon extends js.Array[js.Any]

def GitGraph(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitGraphIcon, color, size)
