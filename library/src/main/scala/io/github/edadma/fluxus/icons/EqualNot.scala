package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/equal-not", JSImport.Default)
private object EqualNotIcon extends js.Array[js.Any]

def EqualNot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EqualNotIcon, color, size)
