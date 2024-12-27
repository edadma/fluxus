package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/signature", JSImport.Default)
private object SignatureIcon extends js.Array[js.Any]

def Signature(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SignatureIcon, color, size)
