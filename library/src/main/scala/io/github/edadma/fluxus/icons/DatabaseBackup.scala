package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/database-backup", JSImport.Default)
private object DatabaseBackupIcon extends js.Array[js.Any]

def DatabaseBackup(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DatabaseBackupIcon, color, size)
