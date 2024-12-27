package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud", JSImport.Default)
private object CloudIcon extends js.Array[js.Any]

def Cloud(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudIcon, color, size)
