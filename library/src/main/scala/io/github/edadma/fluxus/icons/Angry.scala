package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/angry", JSImport.Default)
private object AngryIcon extends js.Array[js.Any]

def Angry(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AngryIcon, color, size)
