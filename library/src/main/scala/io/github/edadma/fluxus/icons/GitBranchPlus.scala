package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-branch-plus", JSImport.Default)
private object GitBranchPlusIcon extends js.Array[js.Any]

def GitBranchPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitBranchPlusIcon, color, size)
