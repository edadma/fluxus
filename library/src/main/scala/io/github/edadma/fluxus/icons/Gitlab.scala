package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gitlab", JSImport.Default)
private object GitlabIcon extends js.Array[js.Any]

def Gitlab(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GitlabIcon, color, size)
