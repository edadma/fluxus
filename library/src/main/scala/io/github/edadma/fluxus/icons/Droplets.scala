package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/droplets", JSImport.Default)
private object DropletsIcon extends js.Array[js.Any]

def Droplets(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DropletsIcon, color, size)
