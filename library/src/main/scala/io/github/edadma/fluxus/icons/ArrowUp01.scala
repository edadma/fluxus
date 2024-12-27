package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/arrow-up-0-1", JSImport.Default)
private object ArrowUp01Icon extends js.Array[js.Any]

def ArrowUp01(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArrowUp01Icon, color, size)
