package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-merge", JSImport.Default)
private object GitMergeIcon extends js.Array[js.Any]

def GitMerge(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitMergeIcon, color, size)
