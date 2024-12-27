package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-left", JSImport.Default)
private object ArrowLeftIcon extends js.Array[js.Any]

def ArrowLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowLeftIcon, color, size)
