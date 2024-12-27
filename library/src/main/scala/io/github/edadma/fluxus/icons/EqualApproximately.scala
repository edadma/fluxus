package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/equal-approximately", JSImport.Default)
private object EqualApproximatelyIcon extends js.Array[js.Any]

def EqualApproximately(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EqualApproximatelyIcon, color, size)
