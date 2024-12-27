package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-pull-request-create", JSImport.Default)
private object GitPullRequestCreateIcon extends js.Array[js.Any]

def GitPullRequestCreate(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitPullRequestCreateIcon, color, size)
