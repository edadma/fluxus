package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sofa", JSImport.Default)
private object SofaIcon extends js.Array[js.Any]

def Sofa(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SofaIcon, color, size)
