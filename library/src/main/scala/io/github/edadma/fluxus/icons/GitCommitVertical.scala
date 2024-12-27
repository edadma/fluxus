package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-commit-vertical", JSImport.Default)
private object GitCommitVerticalIcon extends js.Array[js.Any]

def GitCommitVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitCommitVerticalIcon, color, size)
