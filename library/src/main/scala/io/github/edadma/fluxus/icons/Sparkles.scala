package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sparkles", JSImport.Default)
private object SparklesIcon extends js.Array[js.Any]

def Sparkles(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SparklesIcon, color, size)
