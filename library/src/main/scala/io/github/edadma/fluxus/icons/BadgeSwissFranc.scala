package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/badge-swiss-franc", JSImport.Default)
private object BadgeSwissFrancIcon extends js.Array[js.Any]

def BadgeSwissFranc(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BadgeSwissFrancIcon, color, size)
