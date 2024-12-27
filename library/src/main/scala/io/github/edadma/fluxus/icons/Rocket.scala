package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rocket", JSImport.Default)
private object RocketIcon extends js.Array[js.Any]

def Rocket(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RocketIcon, color, size)
