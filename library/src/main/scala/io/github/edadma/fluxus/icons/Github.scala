package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/github", JSImport.Default)
private object GithubIcon extends js.Array[js.Any]

def Github(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GithubIcon, color, size)
