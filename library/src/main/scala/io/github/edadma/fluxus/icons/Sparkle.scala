package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sparkle", JSImport.Default)
private object SparkleIcon extends js.Array[js.Any]

def Sparkle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SparkleIcon, color, size)
