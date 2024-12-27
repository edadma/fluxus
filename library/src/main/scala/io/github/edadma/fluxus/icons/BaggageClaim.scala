package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/baggage-claim", JSImport.Default)
private object BaggageClaimIcon extends js.Array[js.Any]

def BaggageClaim(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BaggageClaimIcon, color, size)
