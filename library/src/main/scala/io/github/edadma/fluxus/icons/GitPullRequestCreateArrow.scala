package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-pull-request-create-arrow", JSImport.Default)
private object GitPullRequestCreateArrowIcon extends js.Array[js.Any]

def GitPullRequestCreateArrow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitPullRequestCreateArrowIcon, color, size)
