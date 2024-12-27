package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heart", JSImport.Default)
private object HeartIcon extends js.Array[js.Any]

def Heart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeartIcon, color, size)
