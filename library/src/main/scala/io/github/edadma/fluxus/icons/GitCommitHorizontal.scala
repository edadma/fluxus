package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-commit-horizontal", JSImport.Default)
private object GitCommitHorizontalIcon extends js.Array[js.Any]

def GitCommitHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitCommitHorizontalIcon, color, size)
