package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/swiss-franc", JSImport.Default)
private object SwissFrancIcon extends js.Array[js.Any]

def SwissFranc(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SwissFrancIcon, color, size)
