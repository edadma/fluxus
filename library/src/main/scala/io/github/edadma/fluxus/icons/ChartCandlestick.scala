package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-candlestick", JSImport.Default)
private object ChartCandlestickIcon extends js.Array[js.Any]

def ChartCandlestick(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartCandlestickIcon, color, size)
