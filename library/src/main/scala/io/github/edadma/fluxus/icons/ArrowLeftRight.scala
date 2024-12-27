package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-left-right", JSImport.Default)
private object ArrowLeftRightIcon extends js.Array[js.Any]

def ArrowLeftRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowLeftRightIcon, color, size)
