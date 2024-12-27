package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/git-pull-request-draft", JSImport.Default)
private object GitPullRequestDraftIcon extends js.Array[js.Any]

def GitPullRequestDraft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitPullRequestDraftIcon, color, size)
