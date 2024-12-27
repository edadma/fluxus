package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hand-helping", JSImport.Default)
private object HandHelpingIcon extends js.Array[js.Any]

def HandHelping(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HandHelpingIcon, color, size)
