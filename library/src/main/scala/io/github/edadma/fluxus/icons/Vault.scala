package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/vault", JSImport.Default)
private object VaultIcon extends js.Array[js.Any]

def Vault(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VaultIcon, color, size)
