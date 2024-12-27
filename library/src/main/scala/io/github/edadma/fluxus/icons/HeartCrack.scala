package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/heart-crack", JSImport.Default)
private object HeartCrackIcon extends js.Array[js.Any]

def HeartCrack(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeartCrackIcon, color, size)
