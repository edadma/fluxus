package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/toilet", JSImport.Default)
private object ToiletIcon extends js.Array[js.Any]

def Toilet(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ToiletIcon, color, size)
