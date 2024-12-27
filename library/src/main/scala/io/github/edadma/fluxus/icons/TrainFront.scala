package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/train-front", JSImport.Default)
private object TrainFrontIcon extends js.Array[js.Any]

def TrainFront(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrainFrontIcon, color, size)
