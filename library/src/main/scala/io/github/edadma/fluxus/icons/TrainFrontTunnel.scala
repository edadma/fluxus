package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/train-front-tunnel", JSImport.Default)
private object TrainFrontTunnelIcon extends js.Array[js.Any]

def TrainFrontTunnel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrainFrontTunnelIcon, color, size)
