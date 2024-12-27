package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-git", JSImport.Default)
private object FolderGitIcon extends js.Array[js.Any]

def FolderGit(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderGitIcon, color, size)
