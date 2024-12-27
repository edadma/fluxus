package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-1-0", JSImport.Default)
private object ArrowUp10Icon extends js.Array[js.Any]

def ArrowUp10(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUp10Icon, color, size)
