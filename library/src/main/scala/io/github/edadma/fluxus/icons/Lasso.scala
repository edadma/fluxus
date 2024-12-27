package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/lasso", JSImport.Default)
private object LassoIcon extends js.Array[js.Any]

def Lasso(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LassoIcon, color, size)
