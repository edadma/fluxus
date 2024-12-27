package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/eclipse", JSImport.Default)
private object EclipseIcon extends js.Array[js.Any]

def Eclipse(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(EclipseIcon, color, size)
