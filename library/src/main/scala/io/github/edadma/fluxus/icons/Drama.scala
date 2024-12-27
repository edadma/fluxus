package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/drama", JSImport.Default)
private object DramaIcon extends js.Array[js.Any]

def Drama(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DramaIcon, color, size)
