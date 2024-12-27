package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rocking-chair", JSImport.Default)
private object RockingChairIcon extends js.Array[js.Any]

def RockingChair(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RockingChairIcon, color, size)
