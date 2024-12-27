package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-branch", JSImport.Default)
private object GitBranchIcon extends js.Array[js.Any]

def GitBranch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitBranchIcon, color, size)
