package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/star", JSImport.Default)
private object StarIcon extends js.Array[js.Any]

def Star(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StarIcon, color, size)
