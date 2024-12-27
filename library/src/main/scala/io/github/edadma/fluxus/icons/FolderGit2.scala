package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-git-2", JSImport.Default)
private object FolderGit2Icon extends js.Array[js.Any]

def FolderGit2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderGit2Icon, color, size)
