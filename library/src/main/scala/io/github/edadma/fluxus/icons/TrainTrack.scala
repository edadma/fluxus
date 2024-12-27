package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/train-track", JSImport.Default)
private object TrainTrackIcon extends js.Array[js.Any]

def TrainTrack(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrainTrackIcon, color, size)
