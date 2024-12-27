package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-pull-request-closed", JSImport.Default)
private object GitPullRequestClosedIcon extends js.Array[js.Any]

def GitPullRequestClosed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitPullRequestClosedIcon, color, size)
