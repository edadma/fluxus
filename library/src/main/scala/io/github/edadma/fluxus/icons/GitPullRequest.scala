package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-pull-request", JSImport.Default)
private object GitPullRequestIcon extends js.Array[js.Any]

def GitPullRequest(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitPullRequestIcon, color, size)
