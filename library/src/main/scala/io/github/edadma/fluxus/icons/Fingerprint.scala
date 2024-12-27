package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fingerprint", JSImport.Default)
private object FingerprintIcon extends js.Array[js.Any]

def Fingerprint(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FingerprintIcon, color, size)
