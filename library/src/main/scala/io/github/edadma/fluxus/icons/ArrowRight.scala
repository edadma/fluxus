package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-right", JSImport.Default)
private object ArrowRightIcon extends js.Array[js.Any]

def ArrowRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowRightIcon, color, size)
