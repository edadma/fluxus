package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/target", JSImport.Default)
private object TargetIcon extends js.Array[js.Any]

def Target(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TargetIcon, color, size)
