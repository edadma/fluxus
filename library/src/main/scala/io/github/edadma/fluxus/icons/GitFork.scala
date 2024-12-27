package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-fork", JSImport.Default)
private object GitForkIcon extends js.Array[js.Any]

def GitFork(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitForkIcon, color, size)
